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

#if !defined(AFX_EASY_COM_Demo_H__0F2C4136_C293_470B_A6B6_EC9310CF316A__INCLUDED_)
#define AFX_EASY_COM_Demo_H__0F2C4136_C293_470B_A6B6_EC9310CF316A__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"

/////////////////////////////////////////////////////////////////////////////
// CEASY_COM_DemoApp:
//

class CEASY_COM_DemoApp : public CWinApp
{
public:
	CEASY_COM_DemoApp();

	//{{AFX_VIRTUAL(CEASY_COM_DemoApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

	//{{AFX_MSG(CEASY_COM_DemoApp)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_EASY_COM_Demo_H__0F2C4136_C293_470B_A6B6_EC9310CF316A__INCLUDED_)
