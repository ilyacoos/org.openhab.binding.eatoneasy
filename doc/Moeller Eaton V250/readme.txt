====================================================================
Eaton Industries GmbH, Bonn
--------------------------------------------------------------------
EASY_COM.DLL Version 2.5.0
Revision: 16.11.2011
====================================================================

This file contains modifications that were made after the date of 
issue and also details of known errors.

Improvements:
---------------------------------------------------------
V2.5.0
- Expansion of the function range to support EASY80X-DC-SWD
  Read_Object_Value and MC_Read_Object_Value has been extended with new
  object identifiers for reading the operands I1-I128 and Q1-Q128.
  Read_Object_Value, MC_Read_Object_Value and Write_Object_Value, 
  MC_Write_Object_Value now allow marker indizes up to 128.
  
- Demo projects converted to Microsoft Visual Studio 2008 SP1.

- Expansion of the documentation
  Chapter 3.3 and 3.4 have been supplemented by information on
  the use under 32/64-bit environment.
  New Chapter 3.5 "Use with Java..."
  Chapter 4 Introduction with list of data types added.

V2.4.2
- Read_Object_Value und MC_Read_Object_Value now return all analog
  values for easy500/700 (object identifier 8).
  So far only the first analog input was evaluated.

V2.4.0
- Function description to Read_Object_Value and MC_Read_Object_Value
  corrected (parameter 'index', bit marker object 4 and 16).

- Expansion of the function range
  New functions in order to support connections via modem or radio 
  links (Set_UserWaitingTime and MC_Set_UserWaitingTime).

V2.3.0
- Expansion of the function range
  Access to marker ranges does not necessarily have to be based on
  double word boundaries anymore. New object identifier 17
  (marker byte) was added to the Read_Object_Value and
  Write_Object_Value / MC_Read_Object_Value and MC_Write_Object_Value
  access functions, and the "Index" parameter was expanded from
  8 to 16 bits. The interface descriptions in the "Doku"
  subdirectory were adjusted accordingly.
  Existing projects must be compiled again with these files
  in order to be able to use the new EASY_COM.DLL version!
  
- The new "DemoVB.NET" subdirectory includes a small demo
  project that demonstrates how to call EASY_COM.DLL in Microsoft
  Visual Basic 2005 (VB.NET).
  Moreover, a new file, "easyComApi.vb", was added to the"Doku"
  subdirectory.
  This file describes the EASY_COM.DLL interface in VB.NET language.

  For more details, please consult the updated library application
  notes.

V2.2.0
- Expansion of the function range
  Several open connections can now be operated similtaneously
  (Multi-connection mode).
  
- In multi-connection mode the number of markers to be read can be
  given via a length parameter (equivalent to write function).

V2.0.1
- Release of file handle when closing the Ethernet connection.
  Previous behaviour:
  After several opening and closing of an Ethernet connection without 
  a restart of the application all file handles were used up and the 
  function Open_EthernetPort gave the application error code 14 
  until the next new-start.

====================================================================
End of file
====================================================================
