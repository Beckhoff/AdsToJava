#pragma once

#include <stddef.h>
#include <stdint.h>

#ifdef POSIX
typedef uint32_t DWORD;
typedef uint32_t ULONG;
typedef int32_t LONG;
typedef unsigned int BOOL;
#ifndef FAR
#define FAR
#endif
#ifndef NULL
#define NULL 0 // required by the TwinCAT headers
#endif
#define ADSSTDCALL
#else
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#define ADSSTDCALL __stdcall
#endif

#include "TcAdsDef.h"

#ifdef POSIX
typedef ads_i32 ADS_INT32_OR_LONG;
typedef ads_ui32 ADS_UINT32_OR_ULONG;
#else
typedef long ADS_INT32_OR_LONG;
typedef unsigned long ADS_UINT32_OR_ULONG;
#endif
