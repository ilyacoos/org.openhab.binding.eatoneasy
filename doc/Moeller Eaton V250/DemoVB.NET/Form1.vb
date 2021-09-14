'***************************************************************************
' *
' *  easy_COM-DLL V2.5.0
' *  Demo Application for Microsoft Visual Basic 2005/2008 (VB.NET)
' *
' *  Copyright (c) 2011 by Eaton Industries GmbH, Bonn, Germany.
' *  All rights reserved.
' *  Subject to alterations without notice.
' *
' *  All brand and product names are trademarks or registered trademarks of the owner concerned.
' *
' *  See AM_EASY_COM_G.PDF Document for further information.
' *
'****************************************************************************

'****************************************************************************
' using easy-COM interface for multiple connection mode
'
Public Class Form1

	Dim MyComHandle As Integer
	Dim ComError As Integer

	Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

		comboBox_Port.SelectedIndex = 0
		comboBox_Baudrate.SelectedIndex = 1
		MyComHandle = 0

		btn_Connect.Enabled = True
		btn_Disconnect.Enabled = False
		btn_Run_Test.Enabled = False

	End Sub

	Private Sub Form1_FormClosed(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosedEventArgs) Handles MyBase.FormClosed

		easyCOM.MC_CloseAll()

	End Sub

	Private Sub btn_Connect_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Connect.Click

		Dim baud As Integer
		Dim sBaud As String

		Dim waittime As Integer
		easyCOM.MC_Get_UserWaitingTime(waittime)
		easyCOM.MC_Set_UserWaitingTime(1000)
		easyCOM.MC_Get_UserWaitingTime(waittime)

		btn_Run_Test.Enabled = False

		textBox1.Text = "Connecting..."
		textBox1.Refresh()

		ComError = easyCOM.MC_Open_ComPort(MyComHandle, CByte(comboBox_Port.SelectedIndex + 1), Int32.Parse(comboBox_Baudrate.Text))

		If ComError = easyCOM.easy_COM_Err_Success Then

			easyCOM.MC_GetCurrent_Baudrate(MyComHandle, baud)
			sBaud = baud.ToString()

			If (comboBox_Baudrate.Items.Contains(sBaud)) Then
				comboBox_Baudrate.SelectedIndex = comboBox_Baudrate.Items.IndexOf(sBaud)
			End If
			comboBox_Baudrate.Refresh()
			comboBox_Baudrate.Enabled = False
			comboBox_Port.Enabled = False

			textBox1.Text = "Online!"
			btn_Run_Test.Enabled = True

			btn_Connect.Enabled = False
			btn_Disconnect.Enabled = True
			btn_Run_Test.Enabled = True

		Else
			textBox1.Text = "Connection failed. Error " + ComError.ToString()

		End If

	End Sub

	Private Sub btn_Disconnect_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Disconnect.Click

		ComError = easyCOM.MC_Close_ComPort(MyComHandle)

		btn_Connect.Enabled = True
		btn_Disconnect.Enabled = False
		btn_Run_Test.Enabled = False

		comboBox_Baudrate.Enabled = True
		comboBox_Port.Enabled = True

		textBox1.Text = "Offline!"

	End Sub

	Private Sub btn_Run_Test_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_Run_Test.Click

		Dim bYear, bMonth, bDay, bHour, bMin As Byte
		Dim DeviceDateTime As DateTime
		Dim bMarkerByte() As Byte = {1, 2, 3, 4, 5, 6, 7, 8}
		Dim bRawByte(16) As Byte


		textBox1.Text = "Start Test..." + vbCrLf + vbCrLf
		textBox1.Refresh()

		textBox1.Text += "Unlock device: "
		ComError = easyCOM.MC_Unlock_Device(MyComHandle, 0, "123456", bRawByte(0))
		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += "Device unlocked." + vbCrLf
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()

		textBox1.Text += "Systemtime: "
		ComError = easyCOM.MC_Read_Clock(MyComHandle, 0, bYear, bMonth, bDay, bHour, bMin)

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			Try
				DeviceDateTime = New DateTime(bYear + 2000, bMonth, bDay, bHour, bMin, 0)
				textBox1.Text += DeviceDateTime.ToString() + vbCrLf
			Catch ex As ArgumentOutOfRangeException
				textBox1.Text += "???" + vbCrLf
			End Try
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "I16...1: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 0, 0, 0, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += String.Format("hex {1,2:X2}{0,2:X2}", bRawByte(0), bRawByte(1))
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "I128...1: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 18, 0, 0, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += "hex "
			For i As Integer = 15 To 0 Step -1
				textBox1.Text += String.Format("{0,2:X2}", bRawByte(i))
			Next
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "Q16...1: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 1, 0, 0, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += String.Format("hex {1,2:X2}{0,2:X2}", bRawByte(0), bRawByte(1))
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "Q128...1: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 19, 0, 0, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += "hex "
			For i As Integer = 15 To 0 Step -1
				textBox1.Text += String.Format("{0,2:X2}", bRawByte(i))
			Next
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf

		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "ID16...1: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 13, 0, 0, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += String.Format("hex {1,2:X2}{0,2:X2}", bRawByte(0), bRawByte(1))
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "write MD1...MD2: {01 02 03 04 05 06 07 08} "
		ComError = easyCOM.MC_Write_Object_Value(MyComHandle, 0, 10, 1, 8, bMarkerByte(0))

		If (ComError <> easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "read MB1...MB5 + MB6...8: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 17, 1, 5, bRawByte(0))
		If (ComError = easyCOM.easy_COM_Err_Success) Then
			ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 17, 6, 3, bRawByte(5))
		End If

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += String.Format("hex {0,2:X2} {1,2:X2} {2,2:X2} {3,2:X2} {4,2:X2} {5,2:X2} {6,2:X2} {7,2:X2}", _
			bRawByte(0), bRawByte(1), bRawByte(2), bRawByte(3), bRawByte(4), bRawByte(5), bRawByte(6), bRawByte(7))
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "write MD97...MD98: {01 02 03 04 05 06 07 08} "
		ComError = easyCOM.MC_Write_Object_Value(MyComHandle, 0, 10, 97, 8, bMarkerByte(0))

		If (ComError <> easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()


		textBox1.Text += vbCrLf
		textBox1.Text += "read MB385...MB392: "
		ComError = easyCOM.MC_Read_Object_Value(MyComHandle, 0, 17, 385, 8, bRawByte(0))

		If (ComError = easyCOM.easy_COM_Err_Success) Then
			textBox1.Text += String.Format("hex {0,2:X2} {1,2:X2} {2,2:X2} {3,2:X2} {4,2:X2} {5,2:X2} {6,2:X2} {7,2:X2}", _
			bRawByte(0), bRawByte(1), bRawByte(2), bRawByte(3), bRawByte(4), bRawByte(5), bRawByte(6), bRawByte(7))
		Else
			textBox1.Text += "Operation failed. Error " + ComError.ToString() + vbCrLf
		End If
		textBox1.Refresh()

	End Sub

	Private Sub comboBox_Port_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles comboBox_Port.SelectedIndexChanged

	End Sub

	Private Sub comboBox_Baudrate_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles comboBox_Baudrate.SelectedIndexChanged

	End Sub

End Class
