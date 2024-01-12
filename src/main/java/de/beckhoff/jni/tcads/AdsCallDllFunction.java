/*
 * AdsCallDllFunction.java
 */
package de.beckhoff.jni.tcads;

import de.beckhoff.jni.*;

/**
 * Wrapper class for AdsToJava-3.dll or libAdsToJava-3.so.
 *
 * @author Beckhoff Automation
 */
public class AdsCallDllFunction {
    /**
     * Int constant containing the size of the device name buffer.
     */
    public static final short ADS_FIXEDNAMESIZE = 16;
    /**
     * Int constant containing the port number of the standard loggers.
     */
    public static final short AMSPORT_LOGGER = 100;
    /**
     * Int constant containing the port number of the TwinCAT EventLoggers.
     */
    public static final short AMSPORT_EVENTLOG = 110;
    /**
     * Int constant containing the port number of the TwinCAT EventLoggers.
     */
    public static final short AMSPORT_R0_RTIME = 200;
    /**
     * Int constant containing the port number of the TwinCAT Realtime Servers.
     */
    public static final short AMSPORT_R0_TRACE = (AMSPORT_R0_RTIME + 90);
    /**
     * Int constant containing the port number of the TwinCAT I/O Servers.
     */
    public static final short AMSPORT_R0_IO = 300;
    /**
     * Int constant containing the port number of the TwinCAT I/O Servers.
     */
    public static final short AMSPORT_R0_SPS = 400;
    /**
     * Int constant containing the port number of the TwinCAT NC Servers.
     */
    public static final short AMSPORT_R0_NC = 500;
    /**
     * Int constant containing the ort number of the TwinCAT NC Servers
     * (Task SAF).
     */
    public static final short AMSPORT_R0_NCSAF = 501;
    /**
     * Int constant containing the port number of the TwinCAT NC Servers
     * (Task SVB).
     */
    public static final short AMSPORT_R0_NCSVB = 511;
    /**
     * Int constant containing the port number of the TwinCAT NC I Servers.
     */
    public static final short AMSPORT_R0_CNC = 600;
    /**
     * Int constant containing the port number of the TwinCAT PLC Servers
     * (only at the bus-controller).
     */
    public static final short AMSPORT_R0_PLC = 801;
    /**
     * Int constant containing the port number of the TwinCAT PLC Servers in the
     * runtime 1.
     */
    public static final short AMSPORT_R0_PLC_RTS1 = 801;
    /**
     * Int constant containing the port number of the TwinCAT PLC Servers in the
     * runtime 2.
     */
    public static final short AMSPORT_R0_PLC_RTS2 = 811;
    /**
     * Int constant containing the port number of the TwinCAT PLC Servers in the
     * runtime 3.
     */
    public static final short AMSPORT_R0_PLC_RTS3 = 821;
    /**
     * Int constant containing the port number of the TwinCAT PLC Servers in the
     * runtime 4.
     */
    public static final short AMSPORT_R0_PLC_RTS4 = 831;
    /**
     * Int constant containing the port number of the TwinCAT CAM Server.
     */
    public static final long AMSPORT_R0_CAM = 900;
    /**
     * Int constant containing the port number of the TwinCAT CAMTOOL Server.
     */
    public static final long AMSPORT_R0_CAMTOOL = 950;
    /**
     * Int constant containing the port number of the TwinCAT  System Service.
     */
    public static final long AMSPORT_R3_SYSSERV = 10000;

    /**
     * ADS index group: Symbol table
     */
    public static final int ADSIGRP_SYMTAB = 0xF000;
    /**
     * ADS index group: Symbol name
     */
    public static final int ADSIGRP_SYMNAME = 0xF001;
    /**
     * ADS index group: Symbol value
     */
    public static final int ADSIGRP_SYMVAL = 0xF002;
    /**
     * ADS index group: Symbol handle by name
     */
    public static final int ADSIGRP_SYM_HNDBYNAME = 0xF003;
    /**
     * ADS index group: Symbol value by name
     */
    public static final int ADSIGRP_SYM_VALBYNAME = 0xF004;
    /**
     * ADS index group: Symbol value by handle
     */
    public static final int ADSIGRP_SYM_VALBYHND = 0xF005;
    /**
     * ADS index group: Release symbol handle
     */
    public static final int ADSIGRP_SYM_RELEASEHND = 0xF006;
    /**
     * ADS index group: Symbol info by name
     */
    public static final int ADSIGRP_SYM_INFOBYNAME = 0xF007;
    /**
     * ADS index group: Symbol version
     */
    public static final int ADSIGRP_SYM_VERSION = 0xF008;
    /**
     * ADS index group: Symbol info by name
     */
    public static final int ADSIGRP_SYM_INFOBYNAMEEX = 0xF009;
    /**
     * ADS index group: Symbol download
     */
    public static final int ADSIGRP_SYM_DOWNLOAD = 0xF00A;
    /**
     * ADS index group: Symbol upload
     */
    public static final int ADSIGRP_SYM_UPLOAD = 0xF00B;
    /**
     * ADS index group: Symbol upload info
     */
    public static final int ADSIGRP_SYM_UPLOADINFO = 0xF00C;

    /**
     * Int constant containing the IndexGroup to get a notification by a named
     * handle.
     */
    public static final int ADSIGRP_SYMNOTE = 0xF010;
    /**
     * Int constant containing the IndexGroup to read/write input byte(s).
     */
    public static final int ADSIGRP_IOIMAGE_RWIB = 0xF020;
    /**
     * Int constant containing the IndexGroup to read/write an input bit.
     */
    public static final int ADSIGRP_IOIMAGE_RWIX = 0xF021;
    /**
     * Int constant containing the IndexGroup to read/write output byte(s).
     */
    public static final int ADSIGRP_IOIMAGE_RWOB = 0xF030;
    /**
     * Int constant containing the IndexGroup to read/write an output bit.
     */
    public static final int ADSIGRP_IOIMAGE_RWOX = 0xF031;
    /**
     * Int constant containing the IndexGroup to write input to null.
     */
    public static final int ADSIGRP_IOIMAGE_CLEARI = 0xF040;
    /**
     * Int constant containing the IndexGroup to write outputs to null.
     */
    public static final int ADSIGRP_IOIMAGE_CLEARO = 0xF050;
    /**
     * Int constant containing the IndexGroup to get state, name, etc.
     */
    public static final int ADSIGRP_DEVICE_DATA = 0xF100;
    /**
     * Int constant containing the IndexOffset to get the ads state of device.
     */
    public static final int ADSIOFFS_DEVDATA_ADSSTATE = 0x0000;
    /**
     * Int constant containing the IndexOffset to get the device state.
     */
    public static final int ADSIOFFS_DEVDATA_DEVSTATE = 0x0002;

    // AMS reserved events
    /**
     * Int constant containing an EventID indicating that the router has
     * stopped.
     */
    public static final short AMSEVENT_ROUTERSTOP = 0x0;
    /**
     * Int constant containing an EventID indicating that the TwinCAT-Router has
     * started.
     */
    public static final short AMSEVENT_ROUTERSTART = 0x1;
    /**
     * Int constant containing an EventID indicating that the TwinCAT-Router has
     * been removed.
     */
    public static final short AMSEVENT_ROUTERREMOVED = 0x2;

    // Reserved ADS-error codes

    /**
     * Short constant indicating no ADS-error.
     */
    public static final long ADSERR_NO_ERR = 0x00;
    /**
     * Short constant indicating an ADS-error of type "Internal error".
     */
    public static final long ADSERR_INTERNAL_ERR = 0x01;
    /**
     * Short constant indicating an ADS-error of type "No runtime".
     */
    public static final long ADSERR_NO_RUNTIME = 0x02;
    /**
     * Short constant indicating an ADS-error of type "Allocation locked memory
     * error".
     */
    public static final long ADSERR_MEM_ALLOC_LOCK_ERR = 0x03;
    /**
     * Short constant indicating an ADS-error of type "Insert mailbox error".
     */
    public static final long ADSERR_INSERT_MAILBOX_ERR = 0x04;
    /**
     * Short constant indicating an ADS-error of type "Received wrong HMSG".
     */
    public static final long ADSERR_WRONG_HMSG = 0x05;
    /**
     * Short constant indicating an ADS-error of type "Target port not found".
     */
    public static final long ADSERR_PORT_NOT_FOUND = 0x06;
    /**
     * Short constant indicating an ADS-error of type "Target machine not
     * found".
     */
    public static final long ADSERR_MACHINE_NOT_FOUND = 0x07;
    /**
     * Short constant indicating an ADS-error of type "Unknown command ID".
     */
    public static final long ADSERR_UNKN_CMD_ID = 0x08;
    /**
     * Short constant indicating an ADS-error of type "Bad task ID".
     */
    public static final long ADSERR_BAD_TASK_ID = 0x09;
    /**
     * Short constant indicating an ADS-error of type "No IO".
     */
    public static final long ADSERR_NO_IO = 0x0A;
    /**
     * Short constant indicating an ADS-error of type "Unknown AMS command".
     */
    public static final long ADSERR_UNKN_AMS_CMD = 0x0B;
    /**
     * Short constant indicating an ADS-error of type "Win32 error".
     */
    public static final long ADSERR_WIN32_ERR = 0x0C;
    /**
     * Short constant indicating an ADS-error of type "Port not connected".
     */
    public static final long ADSERR_PORT_NOT_CONN = 0x0D;
    /**
     * Short constant indicating an ADS-error of type "Invalid AMS length".
     */
    public static final long ADSERR_INV_AMS_LEN = 0x0E;
    /**
     * Short constant indicating an ADS-error of type "Invalid AMS Net ID".
     */
    public static final long ADSERR_INV_AMS_NETID = 0x0F;
    /**
     * Short constant indicating an ADS-error of type "Low Installation level".
     */
    public static final long ADSERR_LOW_INSTALL_LVL = 0x10;
    /**
     * Short constant indicating an ADS-error of type "No debug available".
     */
    public static final long ADSERR_NO_DEBUG = 0x11;
    /**
     * Short constant indicating an ADS-error of type "Port disabled".
     */
    public static final long ADSERR_PORT_DISABLED = 0x12;
    /**
     * Short constant indicating an ADS-error of type "Port already connected".
     */
    public static final long ADSERR_PORT_ALREADY_CONN = 0x13;
    /**
     * Short constant indicating an ADS-error of type "AMS Sync Win32 error".
     */
    public static final long ADSERR_AMSSYNC_WIN32_ERR = 0x14;
    /**
     * Short constant indicating an ADS-error of type "AMS Sync Timeout".
     */
    public static final long ADSERR_AMSSYNC_TIMEOUT = 0x15;
    /**
     * Short constant indicating an ADS-error of type "AMS Sync AMS error".
     */
    public static final long ADSERR_AMSSYNC_AMS_ERR = 0x16;
    /**
     * Short constant indicating an ADS-error of type "AMS Sync no index map".
     */
    public static final long ADSERR_AMSSYNC_NO_INDEX_MAP = 0x17;
    /**
     * Short constant indicating an ADS-error of type "Invalid AMS port".
     */
    public static final long ADSERR_INV_AMS_PORT = 0x18;
    /**
     * Short constant indicating an ADS-error of type "No memory".
     */
    public static final long ADSERR_NO_MEM = 0x19;
    /**
     * Short constant indicating an ADS-error of type "TCP send error".
     */
    public static final long ADSERR_TCP_SEND_ERR = 0x1A;
    /**
     * Short constant indicating an ADS-error of type "Host unreachable".
     */
    public static final long ADSERR_HOST_UNREACHABLE = 0x1B;

    // Reserved router-error codes

    /**
     * Short constant indicating a router-error of type "No locked memory".
     * Could not allocate any internal memory.
     */
    public static final long ROUTERERR_NOLOCKEDMEMORY = 0x500;
    /**
     * Short constant indicating a router-error of type "Router memory resize
     * failed".
     * The size of the Ams router-memory could not be changed.
     */
    public static final long ROUTERERR_RESIZEMEMORY = 0x501;
    /**
     * Short constant indicating a router-error of type "Specific port's mailbox
     * full".
     * The mailbox of a Ams router-port is has reached its maximum amount of
     * messages. The message sent has been dropped.
     */
    public static final long ROUTERERR_MAILBOXFULL = 0x502;
    /**
     * Short constant indicating a router-error of type "Debug port's mailbox
     * full".
     * The mailbox of the Ams debugger-port has reached its maximum amount of
     * messages. The message sent will not be displayed in the debug monitor.
     */
    public static final long ROUTERERR_DEBUGBOXFULL = 0x503;
    /**
     * Short constant indicating a router-error of type "Unknown port type".
     * The port type is unknown.
     */
    public static final long ROUTERERR_UNKNOWNPORTTYPE = 0x504;
    /**
     * Short constant indicating a router-error of type "Not yet initialized".
     * The router is currently not initialized.
     */
    public static final long ROUTERERR_NOTINITIALIZED = 0x505;
    /**
     * Short constant indicating a router-error of type "Unregistered port".
     * The chosen port is not registered.
     */
    public static final long ROUTERERR_PORTALREADYINUSE = 0x506;
    /**
     * Short constant indicating a router-error of type "No locked memory".
     * Could not allocate any internal memory.
     */
    public static final long ROUTERERR_NOTREGISTERED = 0x507;
    /**
     * Short constant indicating a router-error of type "No more requests".
     * Could not request any more requests. All ports are occupied.
     */
    public static final long ROUTERERR_NOMOREQUEUES = 0x508;
    /**
     * Short constant indicating a router-error of type "Invalid port".
     * The chosen port is invalid.
     */
    public static final long ROUTERERR_INVALIDPORT = 0x509;
    /**
     * Short constant indicating a router-error of type "Router not activated".
     * The TwinCAT router has not been active.
     */
    public static final long ROUTERERR_NOTACTIVATED = 0x50A;

    // Reserved general ads-error codes

    /**
     * Short constant indicating the class of the current ADS-error "Device
     * error".
     */
    public static final long ADSERR_ERRCLASS_DEVICE_ERR = 0x700;
    /**
     * Short constant indicating an ADS-error of type "Service is not supported
     * by server".
     */
    public static final long ADSERR_SRVICE_NOT_SUPP = 0x701;
    /**
     * Short constant indicating an ADS-error of type "Service is not supported
     * by server".
     */
    public static final long ADSERR_SERVICE_NOT_SUPP = 0x701;
    /**
     * Short constant indicating an ADS-error of type "invalid index group".
     */
    public static final long ADSERR_INV_IGRP = 0x702;
    /**
     * Short constant indicating an ADS-error of type "Invalid index offset".
     */
    public static final long ADSERR_INV_IOFF = 0x703;
    /**
     * Short constant indicating an ADS-error of type "Reading/writing not
     * permitted".
     */
    public static final long ADSERR_READWRITE_NOT_PERMIT = 0x704;
    /**
     * Short constant indicating an ADS-error of type "Parameter size not
     * correct".
     */
    public static final long ADSERR_INV_PARAM_SIZE = 0x705;
    /**
     * Short constant indicating an ADS-error of type "Invalid parameter
     * value(s)".
     */
    public static final long ADSERR_INV_PARAM_VALS = 0x706;
    /**
     * Short constant indicating an ADS-error of type "Device is not in a ready
     * state".
     */
    public static final long ADSERR_DEV_NOT_RDY = 0x707;
    /**
     * Short constant indicating an ADS-error of type "Device is busy".
     */
    public static final long ADSERR_DEV_BUSY = 0x708;
    /**
     * Short constant indicating an ADS-error of type "Invalid context (platform
     * has to be Windows)".
     */
    public static final long ADSERR_INV_CONTEXT = 0x709;
    /**
     * Short constant indicating an ADS-error of type "Out of memory".
     */
    public static final long ADSERR_OUTOF_MEM = 0x70A;
    /**
     * Short constant indicating an ADS-error of type "Invalid parameter
     * value(s)".
     */
    public static final long ADSERR_INV_PARAM_VALS2 = 0x70B;
    /**
     * Short constant indicating an ADS-error of type "The specified item could
     * not be found (eg. files, ...)".
     */
    public static final long ADSERR_REQ_ITEM_NOT_FOUND = 0x70C;
    /**
     * Short constant indicating an ADS-error of type "Syntax error in command
     * or file".
     */
    public static final long ADSERR_SYNTAX_ERROR = 0x70D;
    /**
     * Short constant indicating an ADS-error of type "Objects do mismatch".
     */
    public static final long ADSERR_OBJ_MISMATCH = 0x70E;
    /**
     * Short constant indicating an ADS-error of type "Object already exists".
     */
    public static final long ADSERR_OBJ_ALRDY_EXISTS = 0x70F;
    /**
     * Short constant indicating an ADS-error of type "Symbol not found".
     */
    public static final long ADSERR_SYMB_NOT_FOUND = 0x710;
    /**
     * Short constant indicating an ADS-error of type "Symbol version invalid".
     */
    public static final long ADSERR_INV_SYMB = 0x711;
    /**
     * Short constant indicating an ADS-error of type "Server is in invalid
     * state".
     */
    public static final long ADSERR_INV_SERVER_STATE = 0x712;
    /**
     * Short constant indicating an ADS-error of type "ADSTransMode not
     * supported".
     */
    public static final long ADSERR_ADSTRANSMODE_NOT_SUPP = 0x713;
    /**
     * Short constant indicating an ADS-error of type "Notification handle is
     * invalid".
     */
    public static final long ADSERR_INV_NOT_HDL = 0x714;
    /**
     * Short constant indicating an ADS-error of type "Notification client not
     * registered".
     */
    public static final long ADSERR_NOTCLIENT_NOT_REG = 0x715;
    /**
     * Short constant indicating an ADS-error of type "No more notification
     * handles".
     */
    public static final long ADSERR_NOMORE_NOT_HDL = 0x716;
    /**
     * Short constant indicating an ADS-error of type "Size too big to be
     * monitored in watch".
     */
    public static final long ADSERR_SIZE_TOOBIG_FOR_WATCH = 0x717;
    /**
     * Short constant indicating an ADS-error of type "Device is currently not
     * initialized".
     */
    public static final long ADSERR_DEV_NOT_INIT = 0x718;
    /**
     * Short constant indicating an ADS-error of type "Device timed out".
     */
    public static final long ADSERR_DEVICE_TIMEOUT = 0x719;
    /**
     * Short constant indicating an ADS-error of type "Query-interface failed".
     */
    public static final long ADSERR_QUERYINTERFACE_FAILED = 0x71A;
    /**
     * Short constant indicating an ADS-error of type "Wrong interface
     * required".
     */
    public static final long ADSERR_WRONG_INTERFACE_REQ = 0x71B;
    /**
     * Short constant indicating an ADS-error of type "Class-ID is invalid".
     */
    public static final long ADSERR_INV_CLASS_ID = 0x71C;
    /**
     * Short constant indicating an ADS-error of type "Object-ID is invalid".
     */
    public static final long ADSERR_INV_OBJ_ID = 0x71D;
    /**
     * Short constant indicating an ADS-error of type "Request is pending".
     */
    public static final long ADSERR_REQ_PENDING = 0x71E;
    /**
     * Short constant indicating an ADS-error of type "Request is aborted".
     */
    public static final long ADSERR_REQ_ABORTED = 0x71F;
    /**
     * Short constant indicating an ADS-error of type "Signal: Warning".
     */
    public static final long ADSERR_SIGNAL_WARN = 0x720;
    /**
     * Short constant indicating an ADS-error of type "Invalid array index".
     */
    public static final long ADSERR_INV_ARR_INDEX = 0x721;
    /**
     * Short constant indicating an ADS-error of type "Symbol inactive"
     * You might want to try to release the handle and try again".
     */
    public static final long ADSERR_SYMB_NOT_ACTIVE = 0x722;
    /**
     * Short constant indicating an ADS-error of type "Access denied".
     */
    public static final long ADSERR_ACCESS_DENIED = 0x723;

    /**
     * Short constant indicating the class of the current ADS-error "Client
     * error".
     */
    public static final long ADSERR_ERRCLASS_CLIENT_ERR = 0x740;
    /**
     * Short constant indicating an ADS-error of type "Invalid parameter at
     * service".
     */
    public static final long ADSERR_INV_PARAM_AT_SERVICE = 0x741;
    /**
     * Short constant indicating an ADS-error of type "Empty polling list".
     */
    public static final long ADSERR_EMPTY_POLL_LIST = 0x742;
    /**
     * Short constant indicating an ADS-error of type "Variable connection
     * already in use".
     */
    public static final long ADSERR_VAR_CONN_IN_USE = 0x743;
    /**
     * Short constant indicating an ADS-error of type "Invoke ID in use".
     */
    public static final long ADSERR_INVOKE_ID_IN_USE = 0x744;
    /**
     * Short constant indicating an ADS-error of type "Timeout elapsed".
     */
    public static final long ADSERR_TIMEOUT_ELAPSED = 0x745;
    /**
     * Short constant indicating an ADS-error of type "Win32 subsystem-error".
     */
    public static final long ADSERR_WIN32_SUBSYSTEM_ERR = 0x746;
    /**
     * Short constant indicating an ADS-error of type "Invalid client timeout".
     */
    public static final long ADSERR_INV_CLIENT_TIMEOUT = 0x747;
    /**
     * Short constant indicating an ADS-error of type "ADS-port closed".
     */
    public static final long ADSERR_ADSPORT_CLOSED = 0x748;

    /**
     * Short constant indicating the class of the current ADS-error "Internal
     * ADS synchronization error".
     */
    public static final long ADSERR_ERRCLASS_INTERNAL_ERR = 0x750;
    /**
     * Short constant indicating an ADS-error of type "Hashtable overflow".
     */
    public static final long ADSERR_HASHTBL_OVERFLOW = 0x751;
    /**
     * Short constant indicating an ADS-error of type "Key not found in
     * hashtable".
     */
    public static final long ADSERR_HASHTBL_KEY_NOT_FOUND = 0x752;
    /**
     * Short constant indicating an ADS-error of type "No more symbols in
     * cache".
     */
    public static final long ADSERR_NOMORE_SYMB_IN_CACHE = 0x753;
    /**
     * Short constant indicating an ADS-error of type "Invalid response
     * received".
     */
    public static final long ADSERR_INV_RESP_RECEIVED = 0x754;
    /**
     * Short constant indicating an ADS-error of type "Synchronization port is
     * locked".
     */
    public static final long ADSERR_SYNC_PORT_LOCKED = 0x755;

    // Reserved general real time-error codes

    /**
     * Short constant indicating a real time-error of type "Internal real time
     * error".
     * Internal fatal error in the TwinCAT real-time system.
     */
    public static final long RTERR_INTERNAL = 0x1000;
    /**
     * Short constant indicating a real time-error of type "Timer invalid".
     * Timer value not valid
     */
    public static final long RTERR_BADTIMERPERIODS = 0x1001;
    /**
     * Short constant indicating a real time-error of type "Invalid
     * task-pointer".
     * Task pointer has the invalid value ZERO.
     */
    public static final long RTERR_INVALIDTASKPTR = 0x1002;
    /**
     * Short constant indicating a real time-error of type "Invalid
     * stack-pointer".
     * Task stack pointer has the invalid value ZERO.
     */
    public static final long RTERR_INVALIDSTACKPTR = 0x1003;
    /**
     * Short constant indicating a real time-error of type "Priority already
     * assigned.".
     * The demand task priority is already assigned.
     */
    public static final long RTERR_PRIOEXISTS = 0x1004;
    /**
     * Short constant indicating a real time-error of type "No more free TCB".
     * No more free TCB(Task Control Block) available. Maximum number of TCBs
     * is 64.
     */
    public static final long RTERR_NOMORETCB = 0x1005;
    /**
     * Short constant indicating a real time-error of type "No more free
     * semaphores".
     * No more free semaphores available. Maximum number of semaphores is 64.
     */
    public static final long RTERR_NOMORESEMAS = 0x1006;
    /**
     * Short constant indicating a real time-error of type "No more free
     * queue".
     * No more free queues available. Maximum number of queue is 64.
     */
    public static final long RTERR_NOMOREQUEUES = 0x1007;

    /**
     * Short constant indicating a real time-error of type "Synchronization
     * already applied".
     * An external synchronization interrupt is already applied.
     */
    public static final long RTERR_EXTIRQALREADYDEF = 0x100D;
    /**
     * Short constant indicating a real time-error of type "No synchronization
     * applied".
     * No external synchronization interrupt applied.
     */
    public static final long RTERR_EXTIRQNOTDEF = 0x100E;
    /**
     * Short constant indicating a real time-error of type "Applying
     * synchronization failed".
     * Applying the external synchronization interrupt failed.
     */
    public static final long RTERR_EXTIRQINSTALLFAILED = 0x100F;
    /**
     * Short constant indicating a real time-error of type "Wrong service
     * function call".
     * Service function called in wrong context.
     */
    public static final long RTERR_IRQLNOTLESSOREQUAL = 0x1010;

    // Reserved general real winsock-error codes

    /**
     * Short constant indicating a winsock-error of type "Attempted operation at
     * unreachable host".
     * A socket operation was attempted to an unreachable host.
     */
    public static final long WINSKERR_ATT_OPER_UNREACHHOST = 0x274C;
    /**
     * Short constant indicating a winsock-error of type "Connection timeout".
     * A connection attempt failed because the connected party did not properly
     * respond after a period of time, or established connection failed because
     * connected host has failed to respond.
     */
    public static final long WINSKERR_CONN_TIMEOUT = 0x274D;
    /**
     * Short constant indicating a winsock-error of type "Connection refused".
     * No connection could be made because the target machine actively refused
     * it.
     */
    public static final long WINSKERR_CONN_REFUSED = 0x2751;

    /**
     * AdsCallbackObject field containing the listeners to the associated
     * events.
     */
    private static AdsCallbackObject adsCallbackObject = null;

    private static long jniWrapperDllVersion = 0;

    /**
     * Checks whether the version of the wrapper DLL not 1
     * @return true if it is indeed not 1
     */
    public static boolean jniWrapperDllVersionNot1() {
        return jniWrapperDllVersion != 1;
    }

    /**
     * Private constructor to suppress instantiation.
     */
    private AdsCallDllFunction() {}

    /**
     * Load AdsToJava-3.dll or libAdsToJava-3.so.
     */
    static {
        try {
            System.loadLibrary("AdsToJava-3");
        } catch (UnsatisfiedLinkError ex) {
            // We simply did not find the dll
            if (ex.getMessage().equals("no AdsToJava in java.library.path")) {
                System.out.println(
                    "AdsToJava-3.dll or libAdsToJava-3.so not found. Check your PATH environment variable!");

                // An unknown UnsatisfiedLinkError has occurred
            } else {
                System.out.println(ex.getMessage());
            }
        } catch (SecurityException ex) {
            System.out.println(
                "The current thread cannot load the library AdsToJava-3.dll or libAdsToJava-3.so!");
        }

        jniWrapperDllVersion = callDllDoInitDll();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() { callDllDoWhenUnloadDll(); }
        });
    }

    static private native long callDllDoInitDll();

    static private native long callDllDoWhenUnloadDll();

    static private native long callDllDoInitCallbacks(Object lCallBackObj);

    static private void doInitCallbacks() {
        if (adsCallbackObject == null) {
            adsCallbackObject = new AdsCallbackObject();
            callDllDoInitCallbacks(adsCallbackObject);
        }
    }

    /**
     * Returns the associated AdsCallbackObject.
     *
     * @return
     *      an AdsCallbackObject containing information on when and how callback
     *      methods are invoked.
     */
    public static AdsCallbackObject getAdsCallbackObject() {
        doInitCallbacks();
        return new AdsCallbackObject(adsCallbackObject);
    }

    static private native long callDllAddLocalRoute(
        Object lj_AmsNetId, // AMS NetId of ADS server
        String lj_IpAddr);  // IP address, where the ADS server can be found

    static private native long
    callDllDelLocalRoute(Object lj_AmsNetId); // AMS NetId of ADS server

    static private native long
    callDllSetLocalAddress(Object lj_AmsNetId); // AMS NetId of ADS server

    static private native long
    callDllAdsGetDllVersion(AdsVersion lj_AdsVersion);

    static private native long callDllAdsPortOpen();

    static private native long callDllAdsPortClose();

    static private native long callDllAdsGetLocalAddress(AmsAddr lj_AmsAddr);

    static private native long callDllAdsGetLocalAddressn(AmsAddr lj_AmsAddr);

    static private native long callDllAdsSyncReadReq(
        Object lj_AmsAddr,   // Ams address of ADS server
        long lj_indexGroup,  // index group in ADS server interface
        long lj_indexOffset, // index offset in ADS server interface
        long lj_length,      // count of bytes to read
        Object lj_pData);    // pointer to the client buffer

    static private native long callDllAdsSyncWriteReqArray(
        Object lj_AmsAddr,   // Ams address of ADS server
        long lj_indexGroup,  // index group in ADS server interface
        long lj_indexOffset, // index offset in ADS server interface
        long lj_length,      // count of bytes to read
        byte[] lj_pData);    // pointer to the client buffer

    // callDllAdsSyncWriteReqArray is called instead
    //    static private native long callDllAdsSyncWriteReq(
    //            Object lj_AmsAddr,      // Ams address of ADS server
    //            long   lj_indexGroup,   // index group in ADS server interface
    //            long   lj_indexOffset,  // index offset in ADS server
    //            interface long   lj_length,       // count of bytes to read
    //            Object lj_pData);       // pointer to the client buffer

    static private native long callDllAdsSyncReadWriteReq(
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        long lj_lengthRead,    // count of bytes to read
        Object lj_pDataRead,   // pointer to the client buffer
        long lj_lengthWrite,   // count of bytes to write
        Object lj_pDataWrite); // pointer to the client buffer

    static private native long callDllAdsSyncReadStateReq(
        Object lj_AmsAddr,       // Ams address of ADS server
        Object lj_nAdsState,     // pointer to client buffer
        Object lj_nDeviceState); // pointer to the client buffer

    static private native long callDllAdsSyncReadDeviceInfoReq(
        Object lj_AmsAddr,   // Ams address of ADS server
        Object lj_pDevName,  // fixed length string (16 Byte)
        Object lj_pVersion); // client buffer to store server version

    static private native long callDllAdsSyncWriteControlReq(
        Object lj_AmsAddr,  // Ams address of ADS server
        int lj_adsState,    // index group in ADS server interface
        int lj_deviceState, // index offset in ADS server interface
        long lj_length,     // count of bytes to write
        Object lj_pData);   // pointer to the client buffer

    static private native long
    callDllAdsSyncGetTimeout(Object lj_pMs); // Get timeout in ms

    static private native long
    callDllAdsSyncSetTimeout(long lj_nMs); // Set timeout in ms

    static private native long callDllAdsSyncAddDeviceNotificationReq(
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        Object lj_pNoteAttrib, // attributes of notification request
        long lj_hUser,         // user handle
        Object
            lj_pNotification); // pointer to notification handle (return value)

    static private native long callDllAdsSyncDelDeviceNotificationReq(
        Object lj_AmsAddr,        // Ams address of ADS server
        Object lj_hNotification); // notification handle

    static private native long callDllAdsAmsRegisterRouterNotification();

    static private native long callDllAdsAmsUnRegisterRouterNotification();

    static private native long callDllAdsAmsPortEnabled(
        Object lj_pEnabled); // pointer to port status (return value)

    ///////////////////////////////////////////////////////
    // New Ads functions for multithreading applications
    ///////////////////////////////////////////////////////

    static private native long callDllAdsPortOpenEx();

    static private native long
    callDllAdsPortCloseEx(long lj_port); // Ams port of ADS client

    static private native long callDllAdsGetLocalAddressEx(
        long lj_port,        // Ams port of ADS client
        AmsAddr lj_AmsAddr); // Ams address of ADS server

    static private native long callDllAdsSyncReadReqEx(
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        long lj_lengthRead,    // count of bytes to read
        Object lj_pData,       // pointer to the client buffer
        Object lj_pBytesRead); // count of bytes read

    static private native long callDllAdsSyncReadReqEx2(
        long lj_port,          // Ams port of ADS client
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        long lj_lengthRead,    // count of bytes to read
        Object lj_pData,       // pointer to the client buffer
        Object lj_pBytesRead); // count of bytes read

    static private native long callDllAdsSyncWriteReqExArray(
        long lj_port,        // Ams port of ADS client
        Object lj_AmsAddr,   // Ams address of ADS server
        long lj_indexGroup,  // index group in ADS server interface
        long lj_indexOffset, // index offset in ADS server interface
        long lj_length,      // count of bytes to read
        byte[] lj_pData);    // pointer to the client buffer

    // AdsSyncWriteReqExArray is called instead
    //    static private native long callDllAdsSyncWriteReqEx(
    //            long   lj_port,         // Ams port of ADS client
    //            Object lj_AmsAddr,      // Ams address of ADS server
    //            long   lj_indexGroup,   // index group in ADS server interface
    //            long   lj_indexOffset,  // index offset in ADS server
    //            interface long   lj_length,       // count of bytes to read
    //            Object lj_pData);       // pointer to the client buffer

    static private native long callDllAdsSyncReadWriteReqEx(
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        long lj_lengthRead,    // count of bytes to read
        Object lj_pDataRead,   // pointer to the client buffer
        long lj_lengthWrite,   // count of bytes to write
        Object lj_pDataWrite,  // pointer to the client buffer
        Object lj_pBytesRead); // count of bytes read);

    static private native long callDllAdsSyncReadWriteReqEx2(
        long lj_port,          // Ams port of ADS client
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        long lj_lengthRead,    // count of bytes to read
        Object lj_pDataRead,   // pointer to the client buffer
        long lj_lengthWrite,   // count of bytes to write
        Object lj_pDataWrite,  // pointer to the client buffer
        Object lj_pBytesRead); // count of bytes read);

    static private native long callDllAdsSyncReadStateReqEx(
        long lj_port,            // Ams port of ADS client
        Object lj_AmsAddr,       // Ams address of ADS server
        Object lj_nAdsState,     // pointer to client buffer
        Object lj_nDeviceState); // pointer to the client buffer

    static private native long callDllAdsSyncReadDeviceInfoReqEx(
        long lj_port,        // Ams port of ADS client
        Object lj_AmsAddr,   // Ams address of ADS server
        Object lj_pDevName,  // fixed length string (16 Byte)
        Object lj_pVersion); // client buffer to store server version

    static private native long callDllAdsSyncWriteControlReqEx(
        long lj_port,       // Ams port of ADS client
        Object lj_AmsAddr,  // Ams address of ADS server
        int lj_adsState,    // index group in ADS server interface
        int lj_deviceState, // index offset in ADS server interface
        long lj_length,     // count of bytes to write
        Object lj_pData);   // pointer to the client buffer

    static private native long
    callDllAdsSyncGetTimeoutEx(long lj_port,   // Ams port of ADS client
                               Object lj_pMs); // Set timeout in ms

    static private native long
    callDllAdsSyncSetTimeoutEx(long lj_port, // Ams port of ADS client
                               long lj_nMs); // Set timeout in ms

    static private native long callDllAdsSyncAddDeviceNotificationReqEx(
        long lj_port,          // Ams port of ADS client
        Object lj_AmsAddr,     // Ams address of ADS server
        long lj_indexGroup,    // index group in ADS server interface
        long lj_indexOffset,   // index offset in ADS server interface
        Object lj_pNoteAttrib, // attributes of notification request
        long lj_hUser,         // user handle
        Object
            lj_pNotification); // pointer to notification handle (return value)

    static private native long callDllAdsSyncDelDeviceNotificationReqEx(
        long lj_port,             // Ams port of ADS client
        Object lj_AmsAddr,        // Ams address of ADS server
        Object lj_hNotification); // notification handle

    static private native long callDllAdsAmsPortEnabledEx(
        long lj_port,        // Ams port of ADS client
        Object lj_pEnabled); // pointer to port status (return value)

    ///////////////////////////////////////////////////////
    // Java wrapper for AdsCallDllFunction
    ///////////////////////////////////////////////////////

    /**
     * Returns the version number, revision number and build number of the
     * ADS-DLL.
     * @return
     *      an AdsVersion object holding the three items related to the Dll.
     */
    static public AdsVersion adsGetDllVersion() {
        AdsVersion lAdsVersion = new AdsVersion();
        callDllAdsGetDllVersion(lAdsVersion);

        return lAdsVersion;
    }

    /**
     * Establishes a connection (communication port) to the TwinCAT message
     * router.
     *
     * If no TwinCAT MessageRouter is present, the AdsPortOpenEx function will
     * fail.
     *
     * @return
     *      A port number that has been assigned to the program by the ADS
     *      router is returned.
     */
    static public long adsPortOpen() { return callDllAdsPortOpen(); }

    /**
     * The connection (communication port) to the TwinCAT message router is
     * closed.
     *
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsPortClose() { return callDllAdsPortClose(); }

    /**
     * This function returns the local NetId and port number.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long getLocalAddress(AmsAddr lj_AmsAddr) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsGetLocalAddress(lj_AmsAddr);
        }
        return callDllAdsGetLocalAddressn(lj_AmsAddr);
    }

    /**
     * This function reads data synchronously from an ADS server.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be written to the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadReq(AmsAddr lj_AmsAddr, long lj_indexGroup,
                                      long lj_indexOffset, long lj_length,
                                      JNIByteBuffer lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncReadReq(lj_AmsAddr, lj_indexGroup, lj_indexOffset,
                                     lj_length, lj_pData);
    }

    /**
     * This function writes an array of data synchronously to an ADS device.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type byte[]. The buffer holds the data that will be
     *      written to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncWriteReqArray(AmsAddr lj_AmsAddr,
                                            long lj_indexGroup,
                                            long lj_indexOffset, long lj_length,
                                            byte[] lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncWriteReqArray(lj_AmsAddr, lj_indexGroup,
                                           lj_indexOffset, lj_length, lj_pData);
    }

    /**
     * This function writes data synchronously to an ADS device.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncWriteReq(AmsAddr lj_AmsAddr, long lj_indexGroup,
                                       long lj_indexOffset, long lj_length,
                                       JNIByteBuffer lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        return AdsCallDllFunction.adsSyncWriteReqArray(
            lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_length,
            lj_pData.getByteArray());
    }

    /**
     * This function writes data synchronously into an ADS server and receives
     * data back from the ADS device.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_lengthRead
     *      length of lj_pDataRead in bytes. This limits the amount of bytes
     *      that will be written to the buffer.
     * @param lj_pDataRead
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_lengthWrite
     *      length of lj_pDataWrite in bytes. This limits the amount of bytes
     *      that will be read from the buffer.
     * @param lj_pDataWrite
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncReadWriteReq(AmsAddr lj_AmsAddr, long lj_indexGroup,
                        long lj_indexOffset, long lj_lengthRead,
                        JNIByteBuffer lj_pDataRead, long lj_lengthWrite,
                        JNIByteBuffer lj_pDataWrite) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDataRead == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pDataWrite == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncReadWriteReq(
            lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_lengthRead,
            lj_pDataRead, lj_lengthWrite, lj_pDataWrite);
    }

    /**
     * This function reads the ADS status and the device status from an ADS
     * server.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_nAdsState
     *      object of type de.beckhoff.jni.tcads.AdsState. The data will be
     *      written to this object.
     * @param lj_nDeviceState
     *      object of type de.beckhoff.jni.tcads.AdsState. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadStateReq(AmsAddr lj_AmsAddr,
                                           AdsState lj_nAdsState,
                                           AdsState lj_nDeviceState) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_nAdsState == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_nDeviceState == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncReadStateReq(lj_AmsAddr, lj_nAdsState,
                                          lj_nDeviceState);
    }

    /**
     * This function reads the identification and version number of an ADS
     * server.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_pDevName
     *      object of type de.beckhoff.jni.tcads.AdsDevName. The data will be
     *      written to this object.
     * @param lj_pVersion
     *      object of type de.beckhoff.jni.tcads.AdsVersion. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadDeviceInfoReq(AmsAddr lj_AmsAddr,
                                                AdsDevName lj_pDevName,
                                                AdsVersion lj_pVersion) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDevName == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pVersion == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncReadDeviceInfoReq(lj_AmsAddr, lj_pDevName,
                                               lj_pVersion);
    }

    /**
     * This function changes the ADS status and the device status of an ADS
     * server to the defined values.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_adsState
     *      int representing the ADS status that is going to be set to the ADS
     *      server.
     * @param lj_deviceState
     *      int representing the device status that is going to be set to the
     *      ADS server.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server. Information
     *      transferred this way have no further effect.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncWriteControlReq(AmsAddr lj_AmsAddr,
                                              int lj_adsState,
                                              int lj_deviceState,
                                              long lj_length,
                                              JNIByteBuffer lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncWriteControlReq(
            lj_AmsAddr, lj_adsState, lj_deviceState, lj_length, lj_pData);
    }

    /**
     * This function alters the timeout for the ADS functions. The standard
     * value is 5000 ms.
     *
     * @param lj_nMs
     *      long representing the timeout for the Ads functions that is going to
     *      be set to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncSetTimeout(long lj_nMs) {
        return callDllAdsSyncSetTimeout(lj_nMs);
    }

    /**
     * This function retrieves the current timeout for the ADS functions.
     * The standard value is 5000 ms.
     *
     * @param lj_pMs
     *      object of the type de.beckhoff.jni.JNILong. After the call the
     *      current timeout will be contained by this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncGetTimeout(JNILong lj_pMs) {
        if (lj_pMs == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncGetTimeout(lj_pMs);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * function that defines a notification within an ADS server
     * (e.g. PLC). When a certain event occurs a function (the callback
     * function) is invoked in the ADS client.
     *
     * To be able to receive events with the needed information an
     * AdsCallbackObject has to be created and a listener has to be added. The
     * listener has to implement the CallbackListenerAdsState interface. When a
     * certain event occurs the callback function is invoked by the Ads client.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_pNoteAttrib
     *      Object of the type de.beckhoff.jni.tcads.AdsNotificationAttrib that
     *      contains further information on how and when callback methods are
     *      invoked.
     * @param lj_hUser
     *      User handle.
     * @param lj_pNotification
     *      Object of type de.beckhoff.jni.JNILong. The notification handle
     *      will be written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncAddDeviceNotificationReq(AmsAddr lj_AmsAddr, long lj_indexGroup,
                                    long lj_indexOffset,
                                    AdsNotificationAttrib lj_pNoteAttrib,
                                    long lj_hUser, JNILong lj_pNotification) {
        if (lj_AmsAddr == null) {
            return ADSERR_INV_AMS_NETID;
        }
        if (lj_pNoteAttrib == null) {
            return ADSERR_INV_PARAM_VALS2;
        }
        if (lj_pNotification == null) {
            return ADSERR_INV_PARAM_VALS2;
        }

        doInitCallbacks();
        return callDllAdsSyncAddDeviceNotificationReq(
            lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_pNoteAttrib, lj_hUser,
            lj_pNotification);
    }

    /**
     * This function aborts the detection of events with the specified
     * notification.
     *
     * This has no effect on the AdsCallbackObject.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_hNotification
     *      Notification handle.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncDelDeviceNotificationReq(AmsAddr lj_AmsAddr,
                                    JNILong lj_hNotification) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_hNotification == null)
            return ADSERR_INV_PARAM_VALS2;

        return callDllAdsSyncDelDeviceNotificationReq(lj_AmsAddr,
                                                      lj_hNotification);
    }

    /**
     * This function can be used to detect changes in the status of the TwinCAT
     * router.
     *
     * To be able to receive events with the needed information an
     * AdsCallbackObject has to be created and a listener has to be added. The
     * listener has to implement the CallbackListenerAdsRouter interface. The
     * Ads client then invokes the specified onEvent method.
     *
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsAmsRegisterRouterNotification() {
        doInitCallbacks();
        return callDllAdsAmsRegisterRouterNotification();
    }

    /**
     * Monitoring the router's status is ended by this function.
     *
     * This has no effect on the AdsCallbackObject.
     *
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsAmsUnRegisterRouterNotification() {
        return callDllAdsAmsUnRegisterRouterNotification();
    }

    /**
     * This function retrieves a JNIBool indicating whether or not the ads-port
     * is currently open.
     *
     * @param lj_pEnabled
     *      Object of type de.beckhoff.jni.JNIBool. The state of the port
     *      will be written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsAmsPortEnabled(JNIBool lj_pEnabled) {
        if (lj_pEnabled == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsAmsPortEnabled(lj_pEnabled);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    ///////////////////////////////////////////////////////
    // Java wrapper for AdsCallDllFunctionEx
    ///////////////////////////////////////////////////////

    /**
     * Associate an AMS NetId of an ADS server with the IP address where the
     * ADS server can be found.
     *
     * @param lj_AmsNetId
     *      de.beckhoff.jni.tcads.AmsNetId object containing the NetId of the
     *      ADS server.
     * @param lj_IpAddr
     *      IP address, where the ADS server can be found.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsAddLocalRoute(AmsNetId lj_AmsNetId,
                                        String lj_IpAddr) {
        if (lj_AmsNetId == null)
            return ADSERR_INV_AMS_NETID;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAddLocalRoute(lj_AmsNetId, lj_IpAddr);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Remove an association between an AMS NetId and an IP address.
     *
     * @param lj_AmsNetId
     *      de.beckhoff.jni.tcads.AmsNetId object containing the NetId of the
     *      ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsDelLocalRoute(AmsNetId lj_AmsNetId) {
        if (lj_AmsNetId == null)
            return ADSERR_INV_AMS_NETID;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllDelLocalRoute(lj_AmsNetId);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Can be used to adjust the local AMS NetID if the automatic AmsNetId
     * deduction is not working as expected.
     *
     * @param lj_AmsNetId
     *      de.beckhoff.jni.tcads.AmsNetId object containing the NetId of the
     *      ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSetLocalAddress(AmsNetId lj_AmsNetId) {
        if (lj_AmsNetId == null)
            return ADSERR_INV_AMS_NETID;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllSetLocalAddress(lj_AmsNetId);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Establishes a connection (communication port) to the TwinCAT message
     * router. Unlike with AdsPortOpen, a new ADS port is opened each time. The
     * port number returned by AdsPortOpenEx is transferred as parameter to
     * every other ADS function marked as thread-safe.
     *
     * If no TwinCAT MessageRouter is present, the AdsPortOpenEx function will
     * fail.
     *
     * @return
     *      a port number that has been assigned to the program by the ADS
     *      router is returned.
     */
    static public long adsPortOpenEx() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsPortOpenEx();
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * The connection (communication port) to the TwinCAT message router is
     * closed. The port to be closed must previously have been opened via an
     * AdsPortOpenEx call.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsPortCloseEx(long lj_port) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsPortCloseEx(lj_port);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that returns the local NetId and port number.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      object of the type de.beckhoff.jni.tcads.AmsAddr. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long getLocalAddressEx(long lj_port, AmsAddr lj_AmsAddr) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsGetLocalAddressEx(lj_port, lj_AmsAddr);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Extended function that reads data synchronously from an ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes
     * successfully written into the readdata-buffer during the call.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be written to the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     * @deprecated
     *      this method is marked deprecated since it does not correctly return
     *      lj_pBytesRead. Use adsSyncReadReqEx(AmsAddr, long, long, long,
     *      JNIByteBuffer, JNILong) instead.
     */
    @Deprecated
    static public long adsSyncReadReqEx(AmsAddr lj_AmsAddr, long lj_indexGroup,
                                        long lj_indexOffset, long lj_length,
                                        JNIByteBuffer lj_pData,
                                        long lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Dummy buffer to prevent exception in JNI-wrapper
            JNILong pBytesRead = new JNILong();
            return callDllAdsSyncReadReqEx(lj_AmsAddr, lj_indexGroup,
                                           lj_indexOffset, lj_length, lj_pData,
                                           pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Extended function that reads data synchronously from an ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes
     * successfully written into the readdata-buffer during the call.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be written to the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadReqEx(AmsAddr lj_AmsAddr, long lj_indexGroup,
                                        long lj_indexOffset, long lj_length,
                                        JNIByteBuffer lj_pData,
                                        JNILong lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadReqEx(lj_AmsAddr, lj_indexGroup,
                                           lj_indexOffset, lj_length, lj_pData,
                                           lj_pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that reads data synchronously from an ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes written
     * into the data-buffer during the call.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be written to the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     * @deprecated
     *      this method is marked deprecated since it does not correctly return
     *      lj_pBytesRead. Use adsSyncReadReqEx2(long, AmsAddr, long, long,
     *      long, JNIByteBuffer, JNILong) instead.
     */
    @Deprecated
    static public long
    adsSyncReadReqEx2(long lj_port, AmsAddr lj_AmsAddr, long lj_indexGroup,
                      long lj_indexOffset, long lj_length,
                      JNIByteBuffer lj_pData, long lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Dummy buffer to prevent exception in JNI-wrapper
            JNILong pBytesRead = new JNILong();
            return callDllAdsSyncReadReqEx2(lj_port, lj_AmsAddr, lj_indexGroup,
                                            lj_indexOffset, lj_length, lj_pData,
                                            pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that reads data synchronously from an ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes written
     * into the data-buffer during the call.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be written to the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadReqEx2(long lj_port, AmsAddr lj_AmsAddr,
                                         long lj_indexGroup,
                                         long lj_indexOffset, long lj_length,
                                         JNIByteBuffer lj_pData,
                                         JNILong lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadReqEx2(lj_port, lj_AmsAddr, lj_indexGroup,
                                            lj_indexOffset, lj_length, lj_pData,
                                            lj_pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that writes data synchronously to an ADS device.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type byte[]. The buffer holds the data that will be
     *      written to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncWriteReqExArray(long lj_port, AmsAddr lj_AmsAddr,
                                              long lj_indexGroup,
                                              long lj_indexOffset,
                                              long lj_length, byte[] lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncWriteReqExArray(lj_port, lj_AmsAddr,
                                                 lj_indexGroup, lj_indexOffset,
                                                 lj_length, lj_pData);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that writes data synchronously to an ADS device.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncWriteReqEx(long lj_port, AmsAddr lj_AmsAddr,
                                         long lj_indexGroup,
                                         long lj_indexOffset, long lj_length,
                                         JNIByteBuffer lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return AdsCallDllFunction.adsSyncWriteReqExArray(
                lj_port, lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_length,
                lj_pData.getByteArray());
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Extended function that writes data synchronously into an ADS server and
     * receives data back from the ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes
     * successfully written into the data-buffer during the call.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_lengthRead
     *      length of lj_pDataRead in bytes. This limits the amount of bytes
     *      that will be written to the buffer.
     * @param lj_pDataRead
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_lengthWrite
     *      length of lj_pDataWrite in bytes. This limits the amount of bytes
     *      that will be read from the buffer.
     * @param lj_pDataWrite
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     * @deprecated
     *      this method is marked deprecated since it does not correctly return
     *      lj_pBytesRead. Use adsSyncReadWriteReqEx(AmsAddr, long, long, long,
     *      JNIByteBuffer, long, JNIByteBuffer, JNILong) instead.
     */
    @Deprecated
    static public long
    adsSyncReadWriteReqEx(AmsAddr lj_AmsAddr, long lj_indexGroup,
                          long lj_indexOffset, long lj_lengthRead,
                          JNIByteBuffer lj_pDataRead, long lj_lengthWrite,
                          JNIByteBuffer lj_pDataWrite, long lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDataRead == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pDataWrite == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Dummy buffer to prevent exception in JNI-wrapper
            JNILong pBytesRead = new JNILong();
            return callDllAdsSyncReadWriteReqEx(
                lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_lengthRead,
                lj_pDataRead, lj_lengthWrite, lj_pDataWrite, pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Extended function that writes data synchronously into an ADS server and
     * receives data back from the ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes
     * successfully written into the data-buffer during the call.
     *
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_lengthRead
     *      length of lj_pDataRead in bytes. This limits the amount of bytes
     *      that will be written to the buffer.
     * @param lj_pDataRead
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_lengthWrite
     *      length of lj_pDataWrite in bytes. This limits the amount of bytes
     *      that will be read from the buffer.
     * @param lj_pDataWrite
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncReadWriteReqEx(AmsAddr lj_AmsAddr, long lj_indexGroup,
                          long lj_indexOffset, long lj_lengthRead,
                          JNIByteBuffer lj_pDataRead, long lj_lengthWrite,
                          JNIByteBuffer lj_pDataWrite, JNILong lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDataRead == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pDataWrite == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadWriteReqEx(
                lj_AmsAddr, lj_indexGroup, lj_indexOffset, lj_lengthRead,
                lj_pDataRead, lj_lengthWrite, lj_pDataWrite, lj_pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that writes data synchronously into an ADS server
     * and receives data back from the ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes written
     * into the data-buffer during the call.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_lengthRead
     *      length of lj_pDataRead in bytes. This limits the amount of bytes
     *      that will be written to the buffer.
     * @param lj_pDataRead
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_lengthWrite
     *      length of lj_pDataWrite in bytes. This limits the amount of bytes
     *      that will be read from the buffer.
     * @param lj_pDataWrite
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     * @deprecated
     *      this method is marked deprecated since it does not correctly return
     *      lj_pBytesRead. Use adsSyncReadWriteReqEx2(long, AmsAddr, long, long,
     *      long, JNIByteBuffer, long, JNIByteBuffer, JNILong) instead.
     */
    @Deprecated
    static public long
    adsSyncReadWriteReqEx2(long lj_port, AmsAddr lj_AmsAddr, long lj_indexGroup,
                           long lj_indexOffset, long lj_lengthRead,
                           JNIByteBuffer lj_pDataRead, long lj_lengthWrite,
                           JNIByteBuffer lj_pDataWrite, long lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDataRead == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pDataWrite == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Dummy buffer to prevent exception in JNI-wrapper
            JNILong pBytesRead = new JNILong();
            return callDllAdsSyncReadWriteReqEx2(
                lj_port, lj_AmsAddr, lj_indexGroup, lj_indexOffset,
                lj_lengthRead, lj_pDataRead, lj_lengthWrite, lj_pDataWrite,
                pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that writes data synchronously into an ADS server
     * and receives data back from the ADS device.
     *
     * The additional JNILong-buffer will contain the amount of bytes written
     * into the data-buffer during the call.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_lengthRead
     *      length of lj_pDataRead in bytes. This limits the amount of bytes
     *      that will be written to the buffer.
     * @param lj_pDataRead
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The data will be
     *      written to this object.
     * @param lj_lengthWrite
     *      length of lj_pDataWrite in bytes. This limits the amount of bytes
     *      that will be read from the buffer.
     * @param lj_pDataWrite
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server.
     * @param lj_pBytesRead
     *      the full amount of bytes read.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncReadWriteReqEx2(long lj_port, AmsAddr lj_AmsAddr, long lj_indexGroup,
                           long lj_indexOffset, long lj_lengthRead,
                           JNIByteBuffer lj_pDataRead, long lj_lengthWrite,
                           JNIByteBuffer lj_pDataWrite, JNILong lj_pBytesRead) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDataRead == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pDataWrite == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadWriteReqEx2(
                lj_port, lj_AmsAddr, lj_indexGroup, lj_indexOffset,
                lj_lengthRead, lj_pDataRead, lj_lengthWrite, lj_pDataWrite,
                lj_pBytesRead);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that reads the ADS status and the device status from
     * an ADS server.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_nAdsState
     *      object of type de.beckhoff.jni.tcads.AdsState. The data will be
     *      written to this object.
     * @param lj_nDeviceState
     *      object of type de.beckhoff.jni.tcads.AdsState. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadStateReqEx(long lj_port, AmsAddr lj_AmsAddr,
                                             AdsState lj_nAdsState,
                                             AdsState lj_nDeviceState) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_nAdsState == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_nDeviceState == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadStateReqEx(lj_port, lj_AmsAddr,
                                                lj_nAdsState, lj_nDeviceState);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function which reads the identification and version number of
     * an ADS server.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_pDevName
     *      object of type de.beckhoff.jni.tcads.AdsDevName. The data will be
     *      written to this object.
     * @param lj_pVersion
     *      object of type de.beckhoff.jni.tcads.AdsVersion. The data will be
     *      written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncReadDeviceInfoReqEx(long lj_port,
                                                  AmsAddr lj_AmsAddr,
                                                  AdsDevName lj_pDevName,
                                                  AdsVersion lj_pVersion) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pDevName == null)
            return ADSERR_INV_PARAM_VALS2;
        else if (lj_pVersion == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncReadDeviceInfoReqEx(lj_port, lj_AmsAddr,
                                                     lj_pDevName, lj_pVersion);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that changes the ADS status and the device status of
     * an ADS server to the defined values.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_adsState
     *      int representing the ADS status that is going to be set to the ADS
     *      server.
     * @param lj_deviceState
     *      int representing the device status that is going to be set to the
     *      ADS server.
     * @param lj_length
     *      length of lj_pData in bytes. This limits the amount of bytes that
     *      will be read from the buffer.
     * @param lj_pData
     *      object of the type de.beckhoff.jni.JNIByteBuffer. The buffer holds
     *      the data that will be written to the ADS server. Information
     *      transferred this way have no further effect.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncWriteControlReqEx(long lj_port, AmsAddr lj_AmsAddr, int lj_adsState,
                             int lj_deviceState, long lj_length,
                             JNIByteBuffer lj_pData) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_pData == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncWriteControlReqEx(lj_port, lj_AmsAddr,
                                                   lj_adsState, lj_deviceState,
                                                   lj_length, lj_pData);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function to retrieve the timeout for the ADS functions. The
     * default value is 5000 ms.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_pMs
     *      object of the type de.beckhoff.jni.JNILong. After the call the
     *      current timeout will be contained by this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncGetTimeoutEx(long lj_port, JNILong lj_pMs) {
        if (lj_pMs == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncGetTimeoutEx(lj_port, lj_pMs);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function used to alter the timeout for the ADS functions. The
     * default value is 5000 ms.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_nMs
     *      long representing the timeout for the Ads functions that is going to
     *      be set to the ADS server.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsSyncSetTimeoutEx(long lj_port, long lj_nMs) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncSetTimeoutEx(lj_port, lj_nMs);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that defines a notification within an ADS server
     * (e.g. PLC). When a certain event occurs a function (the callback
     * function) is invoked in the ADS client.
     *
     * To be able to receive events with the needed information an
     * AdsCallbackObject has to be created and a listener has to be added. The
     * listener has to implement the CallbackListenerAdsState interface. When a
     * certain event occurs the callback function is invoked by the Ads client.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_indexGroup
     *      Index Group in ADS server interface.
     * @param lj_indexOffset
     *      Index Offset in ADS server interface.
     * @param lj_pNoteAttrib
     *      Object of the type de.beckhoff.jni.tcads.AdsNotificationAttrib that
     *      contains further information on how and when callback methods are
     *      invoked.
     * @param lj_hUser
     *      User handle.
     * @param lj_pNotification
     *      Object of type de.beckhoff.jni.JNILong. The notification handle
     *      will be written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncAddDeviceNotificationReqEx(long lj_port, AmsAddr lj_AmsAddr,
                                      long lj_indexGroup, long lj_indexOffset,
                                      AdsNotificationAttrib lj_pNoteAttrib,
                                      long lj_hUser, JNILong lj_pNotification) {
        if (lj_AmsAddr == null) {
            return ADSERR_INV_AMS_NETID;
        }
        if (lj_pNoteAttrib == null) {
            return ADSERR_INV_PARAM_VALS2;
        }
        if (lj_pNotification == null) {
            return ADSERR_INV_PARAM_VALS2;
        }

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            doInitCallbacks();
            return callDllAdsSyncAddDeviceNotificationReqEx(
                lj_port, lj_AmsAddr, lj_indexGroup, lj_indexOffset,
                lj_pNoteAttrib, lj_hUser, lj_pNotification);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function that deletes a previously defined notification.
     *
     * This has no effect on the AdsCallbackObject.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_AmsAddr
     *      de.beckhoff.jni.tcads.AmsAddr object containing the port number and
     *      the NetId of the ADS server.
     * @param lj_hNotification
     *      Notification handle.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long
    adsSyncDelDeviceNotificationReqEx(long lj_port, AmsAddr lj_AmsAddr,
                                      JNILong lj_hNotification) {
        if (lj_AmsAddr == null)
            return ADSERR_INV_AMS_NETID;
        else if (lj_hNotification == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsSyncDelDeviceNotificationReqEx(lj_port, lj_AmsAddr,
                                                            lj_hNotification);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }

    /**
     * Thread safe function which indicates whether or not the ads-port lj_port
     * is currently open.
     *
     * @param lj_port
     *      port number of an Ads port that had previously been opened with
     *      AdsPortOpenEx.
     * @param lj_pEnabled
     *      Object of type de.beckhoff.jni.JNIBool. The state of the port
     *      will be written to this object.
     * @return
     *      a long value containing the ADSERR.
     */
    static public long adsAmsPortEnabledEx(long lj_port, JNIBool lj_pEnabled) {
        if (lj_pEnabled == null)
            return ADSERR_INV_PARAM_VALS2;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return callDllAdsAmsPortEnabledEx(lj_port, lj_pEnabled);
        } else {
            return ADSERR_SRVICE_NOT_SUPP;
        }
    }
}