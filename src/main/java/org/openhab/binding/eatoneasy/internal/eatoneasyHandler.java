/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.eatoneasy.internal;

import static org.openhab.binding.eatoneasy.internal.eatoneasyBindingConstants.THING_TYPE_EASY800MFD;

import java.io.IOException;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.eatoneasy.internal.comapi.ComApi;
import org.openhab.binding.eatoneasy.internal.comapi.ComApi.DeviceType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link eatoneasyHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Ilyacoos - Initial contribution
 */
@NonNullByDefault
public class eatoneasyHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(eatoneasyHandler.class);

    private @Nullable eatoneasyConfiguration config;

    private ComApi.DeviceType deviceType;
    private ComApi.@Nullable BaudRate baudRate;
    private @Nullable ComApi comApi;

    public eatoneasyHandler(Thing thing, ThingTypeUID thingTypeUID) {
        super(thing);

        if (THING_TYPE_EASY800MFD.equals(thingTypeUID)) {
            this.deviceType = DeviceType.EASY_800_MFD;
        } else {
            throw new IllegalArgumentException("Illegal device type");
        }
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        System.out.println("New command: " + command.toFullString());
        I i = new I(channelUID);
        if (command instanceof RefreshType) {
            System.out.println("Requested refresh of channel: " + channelUID.getId());
            try {
                boolean value = comApi.getValue(config.netId, i.elementType, i.index);
                System.out.println("Got value: " + String.valueOf(value));
                updateState(channelUID, OnOffType.from(value));
            } catch (IllegalArgumentException e) {
                updateStatus(ThingStatus.UNKNOWN, ThingStatusDetail.CONFIGURATION_ERROR,
                        "Error when sending update to device: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        "Communication over COM error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (command instanceof OnOffType) {
            System.out.println("OnOffType");
            boolean enabled = ((OnOffType) command) == OnOffType.ON;
            System.out.println("Switch command: " + String.valueOf(enabled));
            try {
                comApi.setValue(config.netId, i.elementType, i.index, enabled);
                updateState(channelUID, OnOffType.from(enabled));
            } catch (IllegalArgumentException e) {
                updateStatus(ThingStatus.UNKNOWN, ThingStatusDetail.CONFIGURATION_ERROR,
                        "Error when sending update to device: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        "Communication over COM error: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize() {
        config = getConfigAs(eatoneasyConfiguration.class);

        if (config.baudRate == 0) {
            config.baudRate = 19200;
        }
        if (config.timeout == 0) {
            config.timeout = 800;
        }

        switch (config.baudRate) {
            case 4800:
                baudRate = ComApi.BaudRate.BR_4800;
            case 9600:
                baudRate = ComApi.BaudRate.BR_9600;
            case 19200:
                baudRate = ComApi.BaudRate.BR_19200;
            case 38400:
                baudRate = ComApi.BaudRate.BR_38400;
            case 57600:
                baudRate = ComApi.BaudRate.BR_57600;
            default:
                baudRate = ComApi.BaudRate.BR_4800;
        }
        // TODO: Initialize the handler.
        // The framework requires you to return from this method quickly. Also, before leaving this method a thing
        // status from one of ONLINE, OFFLINE or UNKNOWN must be set. This might already be the real thing status in
        // case you can decide it directly.
        // In case you can not decide the thing status directly (e.g. for long running connection handshake using WAN
        // access or similar) you should set status UNKNOWN here and then decide the real status asynchronously in the
        // background.

        // set the thing status to UNKNOWN temporarily and let the background task decide for the real status.
        // the framework is then able to reuse the resources from the thing handler initialization.
        // we set this upfront to reliably check status updates in unit tests.
        updateStatus(ThingStatus.UNKNOWN);

        // Example for background initialization:
        scheduler.execute(() -> {

            try {
                comApi = ComApi.connect(deviceType, config.comPort, baudRate, config.timeout, config.netId,
                        config.password);
            } catch (UnsatisfiedLinkError e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        "Unable to open DLL: " + e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
                        "Bad configuration parameters: " + e.getMessage());
                return;
            } catch (IOException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        "Communication over COM error: " + e.getMessage());
            }

            updateStatus(ThingStatus.ONLINE);

        });

        // These logging types should be primarily used by bindings
        // logger.trace("Example trace message");
        // logger.debug("Example debug message");
        // logger.warn("Example warn message");

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }

    @Override
    public void dispose() {
        scheduler.execute(() -> {

            try {
                comApi.disconnect(config.netId, false);
            } catch (IllegalArgumentException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
                        "Bad configuration parameters: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        "Communication over COM error: " + e.getMessage());
            }

            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.NONE, "Successfully disconnected");

        });
    }

    @Override
    public void handleConfigurationUpdate(Map<String, Object> configurationParameters) {
        if ((int) configurationParameters.get("baudRate") == 0) {
            config.baudRate = 19200;
        }
        if ((int) configurationParameters.get("timeout") == 0) {
            config.timeout = 800;
        }

        switch (config.baudRate) {
            case 4800:
                baudRate = ComApi.BaudRate.BR_4800;
            case 9600:
                baudRate = ComApi.BaudRate.BR_9600;
            case 19200:
                baudRate = ComApi.BaudRate.BR_19200;
            case 38400:
                baudRate = ComApi.BaudRate.BR_38400;
            case 57600:
                baudRate = ComApi.BaudRate.BR_57600;
            default:
                baudRate = ComApi.BaudRate.BR_4800;
        }
    }

    private class I {
        ComApi.ElementType elementType;
        int index;

        I(ChannelUID channelUID) throws IllegalArgumentException {
            switch (channelUID.getId().substring(0, 1)) {
                case "M":
                    elementType = ComApi.ElementType.BIT_MARKER__M1_M96;
                    break;
                default:
                    throw new IllegalArgumentException("Channel ID prefix does not correspond to any component type");
            }

            this.index = Integer.parseInt(channelUID.getId().substring(1));
        }
    }
}
