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

#if !defined(AFX_EASY_COM_DemoDLG_H__81CD86ED_569A_4859_8845_5BC9B931DD5F__INCLUDED_)
#define AFX_EASY_COM_DemoDLG_H__81CD86ED_569A_4859_8845_5BC9B931DD5F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "..\Doku\easyComApi.h"

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoDlg Dialogfeld

class CEASY_COM_DemoDlg : public CDialog
{
public:
	CEASY_COM_DemoDlg(CWnd* pParent = NULL);

	//{{AFX_DATA(CEASY_COM_DemoDlg)
	enum { IDD = IDD_EASY_COM_Demo_DIALOG };
	CDateTimeCtrl	m_ctrl_SystemTime_A;
	CDateTimeCtrl	m_ctrl_SystemDate_A;
	CComboBox	m_ListBox_Baudrate_A;
	CComboBox	m_ListBox_ComPort_A;
	CString	m_csPasswort_A;
	CDateTimeCtrl	m_ctrl_SystemTime_B;
	CDateTimeCtrl	m_ctrl_SystemDate_B;
	CComboBox	m_ListBox_Baudrate_B;
	CComboBox	m_ListBox_ComPort_B;
	CString	m_csPasswort_B;
	//}}AFX_DATA

	//{{AFX_VIRTUAL(CEASY_COM_DemoDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);
	//}}AFX_VIRTUAL

protected:
	HICON m_hIcon;
	tEasyComHandle Handle_A;
	tEasyComHandle Handle_B;

	void ReportError( long error, unsigned char oscode );
	CString ErrorToString( long error, unsigned char oscode );

public:
	//{{AFX_MSG(CEASY_COM_DemoDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnClose();
	afx_msg void OnButtonOnline_A();
	afx_msg void OnButtonOffline_A();
	afx_msg void OnBnClickedButtonRun_A();
	afx_msg void OnBnClickedButtonStop_A();
	afx_msg void OnButton_Get_DT_A();
	afx_msg void OnButton_Set_DT_A();
	afx_msg void OnBtnObjTest_A();
	afx_msg void OnBtnBitmerkerTest_A();
	afx_msg void OnBtnDoppelwortMerkerTest_A();
	afx_msg void OnBtnMBTest_A();
	afx_msg void OnBtnTest_HY_A();
	afx_msg void OnBtnHwTest_A();
	afx_msg void OnBtnLockPw_A();
	afx_msg void OnBtnUnlockPw_A();
	afx_msg void OnKillfocusEditPassword_A();
	afx_msg void OnButtonOnline_B();
	afx_msg void OnButtonOffline_B();
	afx_msg void OnBnClickedButtonRun_B();
	afx_msg void OnBnClickedButtonStop_B();
	afx_msg void OnButton_Get_DT_B();
	afx_msg void OnButton_Set_DT_B();
	afx_msg void OnBtnObjTest_B();
	afx_msg void OnBtnBitmerkerTest_B();
	afx_msg void OnBtnDoppelwortMerkerTest_B();
	afx_msg void OnBtnMBTest_B();
	afx_msg void OnBtnTest_HY_B();
	afx_msg void OnBtnHwTest_B();
	afx_msg void OnBtnLockPw_B();
	afx_msg void OnBtnUnlockPw_B();
	afx_msg void OnKillfocusEditPassword_B();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_EASY_COM_DemoDLG_H__81CD86ED_569A_4859_8845_5BC9B931DD5F__INCLUDED_)
