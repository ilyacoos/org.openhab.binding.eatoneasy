<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Das Formular überschreibt den Löschvorgang, um die Komponentenliste zu bereinigen.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Wird vom Windows Form-Designer benötigt.
    Private components As System.ComponentModel.IContainer

    'Hinweis: Die folgende Prozedur ist für den Windows Form-Designer erforderlich.
    'Das Bearbeiten ist mit dem Windows Form-Designer möglich.  
    'Das Bearbeiten mit dem Code-Editor ist nicht möglich.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
		Me.btn_Run_Test = New System.Windows.Forms.Button
		Me.textBox1 = New System.Windows.Forms.TextBox
		Me.btn_Disconnect = New System.Windows.Forms.Button
		Me.btn_Connect = New System.Windows.Forms.Button
		Me.label2 = New System.Windows.Forms.Label
		Me.comboBox_Baudrate = New System.Windows.Forms.ComboBox
		Me.label1 = New System.Windows.Forms.Label
		Me.comboBox_Port = New System.Windows.Forms.ComboBox
		Me.SuspendLayout()
		'
		'btn_Run_Test
		'
		Me.btn_Run_Test.Location = New System.Drawing.Point(230, 87)
		Me.btn_Run_Test.Name = "btn_Run_Test"
		Me.btn_Run_Test.Size = New System.Drawing.Size(78, 23)
		Me.btn_Run_Test.TabIndex = 15
		Me.btn_Run_Test.Text = "Run Test"
		Me.btn_Run_Test.UseVisualStyleBackColor = True
		'
		'textBox1
		'
		Me.textBox1.Location = New System.Drawing.Point(28, 133)
		Me.textBox1.Multiline = True
		Me.textBox1.Name = "textBox1"
		Me.textBox1.Size = New System.Drawing.Size(556, 249)
		Me.textBox1.TabIndex = 14
		'
		'btn_Disconnect
		'
		Me.btn_Disconnect.Location = New System.Drawing.Point(113, 87)
		Me.btn_Disconnect.Name = "btn_Disconnect"
		Me.btn_Disconnect.Size = New System.Drawing.Size(78, 23)
		Me.btn_Disconnect.TabIndex = 13
		Me.btn_Disconnect.Text = "Disconnect"
		Me.btn_Disconnect.UseVisualStyleBackColor = True
		'
		'btn_Connect
		'
		Me.btn_Connect.Location = New System.Drawing.Point(29, 87)
		Me.btn_Connect.Name = "btn_Connect"
		Me.btn_Connect.Size = New System.Drawing.Size(78, 23)
		Me.btn_Connect.TabIndex = 12
		Me.btn_Connect.Text = "Connect"
		Me.btn_Connect.UseVisualStyleBackColor = True
		'
		'label2
		'
		Me.label2.AutoSize = True
		Me.label2.Location = New System.Drawing.Point(189, 22)
		Me.label2.Name = "label2"
		Me.label2.Size = New System.Drawing.Size(50, 13)
		Me.label2.TabIndex = 11
		Me.label2.Text = "Baudrate"
		'
		'comboBox_Baudrate
		'
		Me.comboBox_Baudrate.FormattingEnabled = True
		Me.comboBox_Baudrate.Items.AddRange(New Object() {"4800", "9600", "19200", "38400", "57600"})
		Me.comboBox_Baudrate.Location = New System.Drawing.Point(189, 38)
		Me.comboBox_Baudrate.Name = "comboBox_Baudrate"
		Me.comboBox_Baudrate.Size = New System.Drawing.Size(124, 21)
		Me.comboBox_Baudrate.TabIndex = 10
		'
		'label1
		'
		Me.label1.AutoSize = True
		Me.label1.Location = New System.Drawing.Point(29, 22)
		Me.label1.Name = "label1"
		Me.label1.Size = New System.Drawing.Size(53, 13)
		Me.label1.TabIndex = 9
		Me.label1.Text = "COM Port"
		'
		'comboBox_Port
		'
		Me.comboBox_Port.FormattingEnabled = True
		Me.comboBox_Port.Items.AddRange(New Object() {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9"})
		Me.comboBox_Port.Location = New System.Drawing.Point(29, 38)
		Me.comboBox_Port.Name = "comboBox_Port"
		Me.comboBox_Port.Size = New System.Drawing.Size(124, 21)
		Me.comboBox_Port.TabIndex = 8
		'
		'Form1
		'
		Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
		Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
		Me.ClientSize = New System.Drawing.Size(615, 409)
		Me.Controls.Add(Me.btn_Run_Test)
		Me.Controls.Add(Me.textBox1)
		Me.Controls.Add(Me.btn_Disconnect)
		Me.Controls.Add(Me.btn_Connect)
		Me.Controls.Add(Me.label2)
		Me.Controls.Add(Me.comboBox_Baudrate)
		Me.Controls.Add(Me.label1)
		Me.Controls.Add(Me.comboBox_Port)
		Me.Name = "Form1"
		Me.Text = "easyCOM VB.NET Demo"
		Me.ResumeLayout(False)
		Me.PerformLayout()

	End Sub
	Private WithEvents btn_Run_Test As System.Windows.Forms.Button
	Private WithEvents textBox1 As System.Windows.Forms.TextBox
	Private WithEvents btn_Disconnect As System.Windows.Forms.Button
	Private WithEvents btn_Connect As System.Windows.Forms.Button
	Private WithEvents label2 As System.Windows.Forms.Label
	Private WithEvents comboBox_Baudrate As System.Windows.Forms.ComboBox
	Private WithEvents label1 As System.Windows.Forms.Label
	Private WithEvents comboBox_Port As System.Windows.Forms.ComboBox

End Class
