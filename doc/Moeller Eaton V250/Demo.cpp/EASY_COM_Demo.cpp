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

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoApp

BEGIN_MESSAGE_MAP(CEASY_COM_DemoApp, CWinApp)
	//{{AFX_MSG_MAP(CEASY_COM_DemoApp)
	//}}AFX_MSG
	ON_COMMAND(ID_HELP, CWinApp::OnHelp)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoApp Konstruktion

CEASY_COM_DemoApp::CEASY_COM_DemoApp()
{
}

/////////////////////////////////////////////////////////////////////////////
// Das einzige CEASY_COM_DemoApp-Objekt

CEASY_COM_DemoApp theApp;

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoApp Initialisierung

BOOL CEASY_COM_DemoApp::InitInstance()
{
	CEASY_COM_DemoDlg dlg;
	m_pMainWnd = &dlg;
	int nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{
	}
	else if (nResponse == IDCANCEL)
	{
	}

	return FALSE;
}
