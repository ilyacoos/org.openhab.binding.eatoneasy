/***************************************************************************
 *
 *  Copyright (c) 2011 by Eaton Industries GmbH, Germany
 *  All rights reserved.
 *  Subject to alterations without notice.
 *
 *  All brand and product names are trademarks or registered trademarks of
 *  the owner concerned.
 *
 *  This application demonstrates the multiple connection mode of the
 *  EASY-COM-DLL ( MS Visual Studio 2008 Project )
 *
 *  See AM_EASY_COM_G.PDF Document for further information.
 *
 ****************************************************************************
 */

#include "stdafx.h"
#include "EASY_COM_Demo.h"
#include "EASY_COM_DemoDlg.h"

#include "..\Doku\easyComApi.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoDlg

CEASY_COM_DemoDlg::CEASY_COM_DemoDlg(CWnd* pParent /*=NULL*/)
:
	CDialog(CEASY_COM_DemoDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CEASY_COM_DemoDlg)
	//}}AFX_DATA_INIT
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CEASY_COM_DemoDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CEASY_COM_DemoDlg)
	DDX_Control(pDX, IDC_DATETIMEPICKER2, m_ctrl_SystemTime_A);
	DDX_Control(pDX, IDC_DATETIMEPICKER1, m_ctrl_SystemDate_A);
	DDX_Control(pDX, IDC_COMBO2, m_ListBox_Baudrate_A);
	DDX_Control(pDX, IDC_COMBO1, m_ListBox_ComPort_A);
	DDX_Text(pDX, IDC_EDIT_PASSWORD, m_csPasswort_A);
	DDX_Control(pDX, IDC_DATETIMEPICKER4, m_ctrl_SystemTime_B);
	DDX_Control(pDX, IDC_DATETIMEPICKER3, m_ctrl_SystemDate_B);
	DDX_Control(pDX, IDC_COMBO4, m_ListBox_Baudrate_B);
	DDX_Control(pDX, IDC_COMBO3, m_ListBox_ComPort_B);
	DDX_Text(pDX, IDC_EDIT_PASSWORD, m_csPasswort_B);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CEASY_COM_DemoDlg, CDialog)
	//{{AFX_MSG_MAP(CEASY_COM_DemoDlg)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDCLOSE, OnClose)

	ON_BN_CLICKED(IDC_BUTTON_ONLINE, OnButtonOnline_A)
	ON_BN_CLICKED(IDC_BUTTON_OFFLINE, OnButtonOffline_A)
	ON_BN_CLICKED(IDC_BUTTON_RUN, OnBnClickedButtonRun_A)
	ON_BN_CLICKED(IDC_BUTTON_STOP, OnBnClickedButtonStop_A)
	ON_BN_CLICKED(IDC_BUTTON_GET_DT, OnButton_Get_DT_A)
	ON_BN_CLICKED(IDC_BUTTON_SET_DT, OnButton_Set_DT_A)
	ON_BN_CLICKED(IDC_BTN_OBJ_TEST, OnBtnObjTest_A)
	ON_BN_CLICKED(IDC_BTN_M_TEST, OnBtnBitmerkerTest_A)
	ON_BN_CLICKED(IDC_BTN_MD_TEST, OnBtnDoppelwortMerkerTest_A)
	ON_BN_CLICKED(IDC_BTN_MB_TEST, OnBtnMBTest_A)
	ON_BN_CLICKED(IDC_BTN_HY, OnBtnTest_HY_A)
	ON_BN_CLICKED(IDC_BTN_HW, OnBtnHwTest_A)
	ON_BN_CLICKED(IDC_BTN_LOCK_PW, OnBtnLockPw_A)
	ON_BN_CLICKED(IDC_BTN_UNLOCK_PW, OnBtnUnlockPw_A)
	ON_EN_KILLFOCUS(IDC_EDIT_PASSWORD, OnKillfocusEditPassword_A)

	ON_BN_CLICKED(IDC_BUTTON_ONLINE2, OnButtonOnline_B)
	ON_BN_CLICKED(IDC_BUTTON_OFFLINE2, OnButtonOffline_B)
	ON_BN_CLICKED(IDC_BUTTON_RUN2, OnBnClickedButtonRun_B)
	ON_BN_CLICKED(IDC_BUTTON_STOP2, OnBnClickedButtonStop_B)
	ON_BN_CLICKED(IDC_BUTTON_GET_DT2, OnButton_Get_DT_B)
	ON_BN_CLICKED(IDC_BUTTON_SET_DT2, OnButton_Set_DT_B)
	ON_BN_CLICKED(IDC_BTN_OBJ_TEST2, OnBtnObjTest_B)
	ON_BN_CLICKED(IDC_BTN_M_TEST2, OnBtnBitmerkerTest_B)
	ON_BN_CLICKED(IDC_BTN_MD_TEST2, OnBtnDoppelwortMerkerTest_B)
	ON_BN_CLICKED(IDC_BTN_MB_TEST2, OnBtnMBTest_B)
	ON_BN_CLICKED(IDC_BTN_HY2, OnBtnTest_HY_B)
	ON_BN_CLICKED(IDC_BTN_HW2, OnBtnHwTest_B)
	ON_BN_CLICKED(IDC_BTN_LOCK_PW2, OnBtnLockPw_B)
	ON_BN_CLICKED(IDC_BTN_UNLOCK_PW2, OnBtnUnlockPw_B)
	ON_EN_KILLFOCUS(IDC_EDIT_PASSWORD2, OnKillfocusEditPassword_B)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoDlg Nachrichten-Handler

BOOL CEASY_COM_DemoDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	SetIcon(m_hIcon, FALSE);

	Handle_A = 0;
	Handle_B = 0;

	CString sPort;
	for ( unsigned i=1; i< 256; i++ )
	{
		sPort.Format( "COM%u", i );
		m_ListBox_ComPort_A.AddString( sPort );
		m_ListBox_ComPort_B.AddString( sPort );
	}

	m_ListBox_Baudrate_A.AddString( "4800" );
	m_ListBox_Baudrate_A.AddString( "9600" );
	m_ListBox_Baudrate_A.AddString( "19200" );
	m_ListBox_Baudrate_A.AddString( "38400" );
	m_ListBox_Baudrate_A.AddString( "57600" );

	m_ListBox_Baudrate_B.AddString( "4800" );
	m_ListBox_Baudrate_B.AddString( "9600" );
	m_ListBox_Baudrate_B.AddString( "19200" );
	m_ListBox_Baudrate_B.AddString( "38400" );
	m_ListBox_Baudrate_B.AddString( "57600" );

	m_ListBox_ComPort_A.SetCurSel( 0 );
	m_ListBox_Baudrate_A.SetCurSel( 1 );

	m_ListBox_ComPort_B.SetCurSel( 0 );
	m_ListBox_Baudrate_B.SetCurSel( 1 );

	CTime dt0(0);
	m_ctrl_SystemDate_A.SetTime( &dt0 );
	m_ctrl_SystemTime_A.SetTime( &dt0 );

	m_ctrl_SystemDate_B.SetTime( &dt0 );
	m_ctrl_SystemTime_B.SetTime( &dt0 );

	SetDlgItemText( IDC_ST_OUTPUT, "" );
	SetDlgItemText( IDC_ST_OUTPUT2, "" );

	return TRUE;
}


void CEASY_COM_DemoDlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this);

		SendMessage( WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		int cxIcon = GetSystemMetrics( SM_CXICON );
		int cyIcon = GetSystemMetrics( SM_CYICON );
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
}

HCURSOR CEASY_COM_DemoDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CEASY_COM_DemoDlg::OnClose() 
{
	MC_CloseAll();
	CDialog::OnCancel();
}

void CEASY_COM_DemoDlg::OnButtonOnline_A() 
{
	int iPortNr = m_ListBox_ComPort_A.GetCurSel();
	int iBaudrate = m_ListBox_Baudrate_A.GetCurSel();
	long baudrate, timeout_delay, err;
	CString s;

	m_ListBox_Baudrate_A.GetLBText( iBaudrate, s );
	baudrate = atol( s );

	{
		CWaitCursor sandbox;

		err = MC_Get_UserWaitingTime( &timeout_delay );
		err = MC_Set_UserWaitingTime( 0 );
		err = MC_Get_UserWaitingTime( &timeout_delay );

		err = MC_Open_ComPort( &Handle_A, LOBYTE( iPortNr+1 ), baudrate );
	}
	ReportError( err, 0 );

	if ( err == 0 || err == 4 )
	{
		err = MC_GetCurrent_Baudrate( Handle_A, &baudrate );
		if ( err == 0 )
		{
			CString s;
			s.Format( "%d", baudrate );
			m_ListBox_Baudrate_A.SelectString(-1, s);
		}
		else
		{
			m_ListBox_Baudrate_A.SetCurSel( LB_ERR );
		}
	}
}

void CEASY_COM_DemoDlg::OnButtonOffline_A() 
{
	long err = MC_Close_ComPort( Handle_A );
	ReportError( err, 0 );
}

void CEASY_COM_DemoDlg::OnBnClickedButtonRun_A()
{
	long err;
	BYTE b;
	err = MC_Start_Program( Handle_A, 0, &b );
	ReportError( err, b );
}

void CEASY_COM_DemoDlg::OnBnClickedButtonStop_A()
{
	long err;
	BYTE b;
	err = MC_Stop_Program( Handle_A, 0, &b );
	ReportError( err, b );
}

CString CEASY_COM_DemoDlg::ErrorToString( long error, unsigned char oscode )
{
	static const char* szErrTxt[] = { 
		"Success",
		"InvalidParameter",
		"DeviceAdverseResponse",
		"DeviceNoResponse",
		"DevicePasswortLock",
		"DeviceTypeUnknown",
		"WindowsSystemError",
		"ComPortNotOpen",
		"ComPortNotExist",
		"ComPortCannotAccessed",
		"ComGeneralError",
		"InternalError",
		"FB_NotExist",
		"FB_NoConstParameter",
		"TcpPortNotExist",
		"TcpPortNoBaudrateScanPossible",
		"HandleInvalid",
	};

	if ( error == 0 )
		return "OK\n";

	CString s;
	if ( error == 2 )
		s.Format( "Error code 2, device error message %u\n", oscode );
	else
		s.Format( "Error code %d (%s)\n", error, szErrTxt[error] );

	return s;
}

void CEASY_COM_DemoDlg::ReportError( long error, unsigned char oscode )
{
	if ( error != 0 )
	{
		CString s;
		s = ErrorToString( error, oscode );

		::AfxMessageBox( s, MB_OK, -1 );
	}
}


void CEASY_COM_DemoDlg::OnButton_Get_DT_A() 
{
	unsigned char year;
	unsigned char month;
	unsigned char day;
	unsigned char hour;
	unsigned char min;
	long err;

	{
		CWaitCursor sandbox;
		err = MC_Read_Clock( Handle_A, 0, &year, &month, &day, &hour, &min );
	}

	if ( err == 0 )
	{
		CTime dt(0);
		if ( month > 0 && day > 0 )
			dt = CTime( 2000+year, month, day, hour, min, 0, -1 );
		m_ctrl_SystemDate_A.SetTime( &dt );
		m_ctrl_SystemTime_A.SetTime( &dt );
	}
	ReportError( err, year );
}

void CEASY_COM_DemoDlg::OnButton_Set_DT_A() 
{
	unsigned char year;
	unsigned char month;
	unsigned char day;
	unsigned char hour;
	unsigned char min;
	long err;

	CTime d,t;

	m_ctrl_SystemDate_A.GetTime( d );
	m_ctrl_SystemTime_A.GetTime( t );

	year = LOBYTE( d.GetYear() - 2000 );
	month = LOBYTE( d.GetMonth() );
	day = LOBYTE( d.GetDay() );

	hour = LOBYTE( t.GetHour() );
	min = LOBYTE( t.GetMinute() );

	{
		CWaitCursor sandbox;
		err = MC_Write_Clock( Handle_A, 0, &year, &month, &day, &hour, &min );
	}
	ReportError( err, year );
}

void CEASY_COM_DemoDlg::OnBtnObjTest_A() 
{
	long error;
	unsigned char data[32] = {0};
	WORD* pW = (WORD*) data;
	DWORD* pDW = (DWORD*) data;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "I16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 0, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "I128...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 18, 0, 0, data );
		if ( error == 0 )
		{
			for( int n=15; n>=0; n-- )
			{
				csTmp.Format( "%2.2X ", data[n] );
				csOutput += csTmp;
			}
			csOutput += "\n";
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "Q8...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 1, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X \n", data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "Q128...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 19, 0, 0, data );
		if ( error == 0 )
		{
			for( int n=15; n>=0; n-- )
			{
				csTmp.Format( "%2.2X ", data[n] );
				csOutput += csTmp;
			}
			csOutput += "\n";
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "R16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 2, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "S8...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 3, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X \n", data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "ID16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 13, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "ID32...17: ";
		error = MC_Read_Object_Value( Handle_A, 0, 14, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "P4...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 5, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X \n", data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "LE3...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 15, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X \n", data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "IA4...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 8, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%4.4X %4.4X %4.4X %4.4X \n", pW[3], pW[2], pW[1], pW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "QA1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 9, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%4.4X \n", pW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
	}

	SetDlgItemText( IDC_ST_OUTPUT, csOutput );
}

void CEASY_COM_DemoDlg::OnBtnBitmerkerTest_A() 
{
	long error;
	bool isEasy500700;
	unsigned char bData;
	unsigned char data[32] = {0};
	WORD* pW = (WORD*) data;
	DWORD* pDW = (DWORD*) data;

	CString csOutput, csTmp;

	error = MC_Read_Object_Value( Handle_A, 0, 16, 0, 0, data ); // N-Marker-Test
	isEasy500700 = ( error == 0 );
	if ( isEasy500700 )
	{
		CWaitCursor Sanduhr;

		csOutput += "reading M16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing M1...M16 = 1 \n";
		for ( int i=1; i<=16; i++ )
		{
			bData = 1;
			error = MC_Write_Object_Value( Handle_A, 0, 4, i, 0, &bData );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading M16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing M1...M16 = 0 \n";
		for ( int j=1; j<=16; j++ )
		{
			bData = 0;
			error = MC_Write_Object_Value( Handle_A, 0, 4, j, 0, &bData );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading M16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading N16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 16, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing N1...N16 = 1 \n";
		for ( int k=1; k<=16; k++ )
		{
			bData = 1;
			error = MC_Write_Object_Value( Handle_A, 0, 16, k, 0, &bData );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading N16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 16, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%2.2X %2.2X \n", data[1], data[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing N1...N16 = 0 \n";
		for ( int l=1; l<=16; l++ )
		{
			bData = 0;
			error = MC_Write_Object_Value( Handle_A, 0, 16, l, 0, &bData );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading N16...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 16, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%4.4X \n", pW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}
	else
	{
		CWaitCursor Sanduhr;

		csOutput += "reading M96...1: ";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X \n", pDW[2], pDW[1], pDW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "writing M1...M96 = 1 \n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		for ( int i=1; i<=96; i++ )
		{
			bData = 1;
			error = MC_Write_Object_Value( Handle_A, 0, 4, i, 0, &bData );
		}

		csOutput += "reading M96...1: ";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X \n", pDW[2], pDW[1], pDW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}

		csOutput += "writing M1...M96 = 0 \n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		for ( int j=1; j<=96; j++ )
		{
			bData = 0;
			error = MC_Write_Object_Value( Handle_A, 0, 4, j, 0, &bData );
		}

		csOutput += "reading M96...1: ";
		error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X \n", pDW[2], pDW[1], pDW[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, data[0] );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		//-- marker extensions since V2.5.0
		bool bXtraMarkerSupported = true;
		csOutput += "writing M97...M128 = 1 \n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		for ( int k=97; k<=128; k++ )
		{
			bData = 1;
			error = MC_Write_Object_Value( Handle_A, 0, 4, k, 0, &bData );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData );
				bXtraMarkerSupported = false;
				break;
			}
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		if ( bXtraMarkerSupported )
		{
			csOutput += "reading M128...1: ";
			error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
			if ( error == 0 )
			{
				csTmp.Format( "%8.8X %8.8X %8.8X %8.8X \n", pDW[3], pDW[2], pDW[1], pDW[0] );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, data[0] );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );

			csOutput += "writing M97...M128 = 0 \n";
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
			for ( int j=97; j<=128; j++ )
			{
				bData = 0;
				error = MC_Write_Object_Value( Handle_A, 0, 4, j, 0, &bData );
				if ( error != 0 )
					break;
			}

			csOutput += "reading M128...1: ";
			error = MC_Read_Object_Value( Handle_A, 0, 4, 0, 0, data );
			if ( error == 0 )
			{
				csTmp.Format( "%8.8X %8.8X %8.8X %8.8X \n", pDW[3], pDW[2], pDW[1], pDW[0] );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, data[0] );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}
}

void CEASY_COM_DemoDlg::OnBtnDoppelwortMerkerTest_A() 
{
	long error;
	DWORD dwData[8] = {0};

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "reading MD8...MD1:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 1, 4*8, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[7], dwData[6], dwData[5], dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing MD6...MD1:\n";
		for(int i=0; i<8; i++ )
			dwData[i] = i*256+i;
		error = MC_Write_Object_Value( Handle_A, 0, 10, 1, 4*6, (unsigned char*)dwData );
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading MD8...MD1:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 1, 4*8, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[7], dwData[6], dwData[5], dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading MD20...MD16:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 16, 4*5, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "writing MD16...MD24:\n";
		for(int j=0; j<=8; j++ )
		{
			dwData[0] = j;
			error = MC_Write_Object_Value( Handle_A, 0, 10, 16+j, 4, (unsigned char*)dwData );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		csOutput += "reading MD23...MD16:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 16, 4*8, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[7], dwData[6], dwData[5], dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		// new marker extensions since V2.5.0

		csOutput += "writing MD103...MD96:\n";
		for(int j=96; j<=103; j++ )
			dwData[j-96] = j;

		error = MC_Write_Object_Value( Handle_A, 0, 10, 96, 4*8, (unsigned char*)dwData );
		if ( error != 0 )
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
			return;
		}

		csOutput += "reading MD103...MD96:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 96, 4*8, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[7], dwData[6], dwData[5], dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );


		csOutput += "writing MD121...MD128:\n";
		for(int j=121; j<=128; j++ )
			dwData[j-121] = j;

		error = MC_Write_Object_Value( Handle_A, 0, 10, 121, 4*8, (unsigned char*)dwData );
		if ( error != 0 )
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
			return;
		}

		csOutput += "reading MD128...MD121:\n";
		error = MC_Read_Object_Value( Handle_A, 0, 10, 121, 4*8, (unsigned char*)dwData );
		if ( error == 0 )
		{
			csTmp.Format( "%8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X %8.8X \n", dwData[7], dwData[6], dwData[5], dwData[4], dwData[3], dwData[2], dwData[1], dwData[0] );
			csOutput += csTmp;
		}
		else
		{
			csOutput += ErrorToString( error, LOBYTE( dwData[0] ) );
		}
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}
}

void CEASY_COM_DemoDlg::OnBtnMBTest_A() 
{
	BYTE bData[512];
	long error;
	BOOL ok;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "writing single byte MB1...MB384:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for (int i=0; i<384; i++) // Init
			bData[i] = LOBYTE(i);

		for (int j=0; j<384; j++) // Schreiben
		{
			error = MC_Write_Object_Value( Handle_A, 0, 17, 1+j, 1, bData+j );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[j] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
		}

		csOutput += "verify single byte MB1...MB384:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		ok = TRUE;
		memset( bData, 0, 384 );
		for (int k=0; k<384; k++) // Verify
		{
			error = MC_Read_Object_Value( Handle_A, 0, 17, 1+k, 1, bData+k );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[k] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
			else if ( bData[k] != LOBYTE(k) )
			{
				ok = FALSE;
			}
		}
		if ( !ok )
			csOutput += " not ok\n";
		else
			csOutput += " ok\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "writing variable blocks MB1...MB384:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for (int i=0; i<384; i++) // Init
			bData[i] = LOBYTE(i);

		for (int j=0; j<384; j++) // Schreiben
		{
			int len = j%80 + 1; // 1..80
			if ( len+j > 384 )
				len = 384 - j;
			error = MC_Write_Object_Value( Handle_A, 0, 17, 1+j, len, bData+j );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[j] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
			j += len;
		}

		csOutput += "verify variable blocks MB1...MB384:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		memset( bData, 0, 384 );
		int len=0;
		for (int k=0; k<384; k++) // Verify
		{
			++len;
			if ( len+k > 384 )
				len = 384 - k;
			// special test: memset( bData+k, 255, 384-k );
			error = MC_Read_Object_Value( Handle_A, 0, 17, 1+k, len, bData+k );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[k] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
			k += len-1;
		}
		ok = TRUE;
		for (int l=0; l<384; l++) // Verify
		{
			if ( bData[l] != LOBYTE(l) )
			{
				ok = FALSE;
				break;
			}
		}
		if ( !ok )
			csOutput += " not ok\n";
		else
			csOutput += " ok\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}

	// new marker extensions since V2.5.0
	{
		CWaitCursor Sanduhr;

		csOutput += "writing single byte MB90...MB512:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for (int i=89; i<512; i++) // Init
			bData[i] = LOBYTE(i);

		for (int j=89; j<512; j++) // Schreiben
		{
			error = MC_Write_Object_Value( Handle_A, 0, 17, 1+j, 1, bData+j );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[j] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
		}

		csOutput += "verify single byte MB90...MB512:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		ok = TRUE;
		memset( bData, 0, 512 );
		for (int k=89; k<512; k++) // Verify
		{
			error = MC_Read_Object_Value( Handle_A, 0, 17, 1+k, 1, bData+k );
			if ( error != 0 )
			{
				csOutput += ErrorToString( error, bData[k] );
				SetDlgItemText( IDC_ST_OUTPUT, csOutput );
				return;
			}
			else if ( bData[k] != LOBYTE(k) )
			{
				ok = FALSE;
			}
		}
		if ( !ok )
			csOutput += " not ok\n";
		else
			csOutput += " ok\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}

}

void CEASY_COM_DemoDlg::OnBtnTest_HY_A() 
{
	long error;
	BYTE OnYear=0, OffYear=0, OnMonth=0, OffMonth=0, OnDay=0, OffDay=0;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "reading HY1:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			error = MC_Read_Channel_YearTimeSwitch( Handle_A, 0, 1, channel, &OnYear, &OnMonth, &OnDay, &OffYear, &OffMonth, &OffDay );
			if ( error == 0 )
			{
				csTmp.Format( "  channel %c - D%2.2d...%2.2d  M%2.2d...%2.2d  Y%2.2d...%2.2d \n", 'A'+channel, OnDay, OffDay, OnMonth, OffMonth, OnYear, OffYear );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, OnYear );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "writing HY1:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			csTmp.Format( "  channel %c - ", 'A'+channel );
			csOutput += csTmp;
			error = MC_Read_Channel_YearTimeSwitch( Handle_A, 0, 1, channel, &OnYear, &OnMonth, &OnDay, &OffYear, &OffMonth, &OffDay );
			if ( error == 0 )
				error = MC_Write_Channel_YearTimeSwitch( Handle_A, 0, 1, channel, &OnYear, &OnMonth, &OnDay, &OffYear, &OffMonth, &OffDay );
			if ( error == 0 )
			{
				csOutput += "OK\n";
			}
			else
			{
				csOutput += ErrorToString( error, OnYear );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "reading HY4:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			error = MC_Read_Channel_YearTimeSwitch( Handle_A, 0, 3, channel, &OnYear, &OnMonth, &OnDay, &OffYear, &OffMonth, &OffDay );
			if ( error == 0 )
			{
				csTmp.Format( "  channel %c - D%2.2d...%2.2d  M%2.2d...%2.2d  Y%2.2d...%2.2d \n", 'A'+channel, OnDay, OffDay, OnMonth, OffMonth, OnYear, OffYear );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, OnYear );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "writing HY4-channel B:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		{
			error = MC_Write_Channel_YearTimeSwitch( Handle_A, 0, 3, 1, &OnYear, &OnMonth, &OnDay, &OffYear, &OffMonth, &OffDay );
			if ( error == 0 )
			{
				csOutput += "OK";
			}
			else
			{
				csOutput += ErrorToString( error, OnYear );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}
}

void CEASY_COM_DemoDlg::OnBtnHwTest_A() 
{
	long error;
	BYTE Day1=0, Day2=0, OnHour=0, OffHour=0, OnMinute=0, OffMinute=0;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "reading HW1:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			error = MC_Read_Channel_7DayTimeSwitch( Handle_A, 0, 1, channel, &Day1, &Day2, &OnHour, &OnMinute, &OffHour, &OffMinute );
			if ( error == 0 )
			{
				csTmp.Format( "  channel %c - DY1=%d  DY2=%d  ON=%2.2d:%2.2d  OFF=%2.2d:%2.2d \n", 'A'+channel, Day1, Day2, OnHour, OnMinute, OffHour, OffMinute );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, Day1 );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "writing HW1:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			csTmp.Format( "  channel %c - ", 'A'+channel );
			csOutput += csTmp;
			error = MC_Read_Channel_7DayTimeSwitch( Handle_A, 0, 1, channel, &Day1, &Day2, &OnHour, &OnMinute, &OffHour, &OffMinute );
			if ( error == 0 )
				error = MC_Write_Channel_7DayTimeSwitch( Handle_A, 0, 1, channel, &Day1, &Day2, &OnHour, &OnMinute, &OffHour, &OffMinute );
			if ( error == 0 )
			{
				csOutput += "OK\n";
			}
			else
			{
				csOutput += ErrorToString( error, Day1 );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "reading HW4:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		for ( int channel=0; channel<4; channel++ )
		{
			error = MC_Read_Channel_7DayTimeSwitch( Handle_A, 0, 3, channel, &Day1, &Day2, &OnHour, &OnMinute, &OffHour, &OffMinute );
			if ( error == 0 )
			{
				csTmp.Format( "  channel %c - DY1=%d  DY2=%d  ON=%2.2d:%2.2d  OFF=%2.2d:%2.2d \n", 'A'+channel, Day1, Day2, OnHour, OnMinute, OffHour, OffMinute );
				csOutput += csTmp;
			}
			else
			{
				csOutput += ErrorToString( error, Day1 );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

	{
		CWaitCursor Sanduhr;

		csOutput += "writing HW4-channel B:\n";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		{
			error = MC_Write_Channel_7DayTimeSwitch( Handle_A, 0, 3, 1, &Day1, &Day2, &OnHour, &OnMinute, &OffHour, &OffMinute );
			if ( error == 0 )
			{
				csOutput += "OK";
			}
			else
			{
				csOutput += ErrorToString( error, Day1 );
			}
			SetDlgItemText( IDC_ST_OUTPUT, csOutput );
		}
	}

}

void CEASY_COM_DemoDlg::OnKillfocusEditPassword_A() 
{
	TCHAR cBuffer[20];
	GetDlgItemText( IDC_EDIT_PASSWORD, cBuffer, 19 );

	m_csPasswort_A = cBuffer;
}

void CEASY_COM_DemoDlg::OnBtnLockPw_A() 
{
	long error;
	BYTE errorcode;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "Lock device...";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		error = MC_Lock_Device( Handle_A, 0, &errorcode );
		csOutput += ErrorToString( error, errorcode );

		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}
}

void CEASY_COM_DemoDlg::OnBtnUnlockPw_A() 
{
	long error;
	BYTE errorcode;

	CString csOutput, csTmp;

	{
		CWaitCursor Sanduhr;

		csOutput += "Unlock device...";
		SetDlgItemText( IDC_ST_OUTPUT, csOutput );

		error = MC_Unlock_Device( Handle_A, 0, (LPCSTR) m_csPasswort_A, &errorcode );
		csOutput += ErrorToString( error, errorcode );

		SetDlgItemText( IDC_ST_OUTPUT, csOutput );
	}
}
