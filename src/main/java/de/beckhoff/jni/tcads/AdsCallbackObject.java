/*
 * AdsCallbackObject.java
 */
package de.beckhoff.jni.tcads;

import de.beckhoff.jni.JNILong;
import java.util.Vector;

/**
 * This class contains de.beckhoff.jni.tcads.CallbackListenerAdsRouter and
 * de.beckhoff.jni.tcads.CallbackListenerAdsState. Those listeners callback
 * methods are invoked through this classes doAdsRouterCallback and
 * doAdsStateCallback methods.
 *
 * @author Beckhoff Automation
 */
public class AdsCallbackObject {
    private static Vector<CallbackListenerAdsRouter>
        mCallbackListenersAdsRouter = new Vector<CallbackListenerAdsRouter>();
    private static Vector<CallbackListenerAdsState> mCallbackListenersAdsState =
        new Vector<CallbackListenerAdsState>();

    /**
     * Class constructor
     */
    public AdsCallbackObject() {}

    /**
     * Class copy constructor.
     * @param obj
     *      another AdsCallbackObject object.
     */
    public AdsCallbackObject(AdsCallbackObject obj) {
        // there are no members to copy
    }

    /**
     * This method informs all declared listeners about
     * TwinCAT router events via processCallbackAdsState.
     *
     * @param nReason
     *      a de.beckhoff.jni.JNILong containing the new status of the router.
     */
    protected void doAdsRouterCallback(JNILong nReason) {
        processCallbackAdsRouter(nReason.getLong());
    }

    /**
     * This method adds a new listener to the list of current listeners. Each
     * listeners onEvent method is invoked on an event.
     *
     * @param listener
     *      the listener implementing the CallbackListenerAdsRouter interface.
     */
    public void
    addListenerCallbackAdsRouter(CallbackListenerAdsRouter listener) {
        mCallbackListenersAdsRouter.addElement(listener);
    }

    /**
     * This method removes a certain listener from the list of current
     * listeners.
     *
     * @param listener
     *      the listener to be removed.
     */
    public void
    removeListenerCallbackAdsRouter(CallbackListenerAdsRouter listener) {
        mCallbackListenersAdsRouter.removeElement(listener);
    }

    /**
     * This method informs all declared listeners about
     * TwinCAT router events. OnEvent methods are going to be invoked.
     *
     * @param nReason
     *      the new router state that has been the reason for the event.
     */
    public void processCallbackAdsRouter(long nReason) {
        for (int i = 0; i < mCallbackListenersAdsRouter.size(); i++) {
            ((CallbackListenerAdsRouter)mCallbackListenersAdsRouter.elementAt(
                 i))
                .onEvent(nReason);
        }
    }

    /**
     * This method informs all declared listeners about any events (e.g.
     * variable change events) via processCallbackAdsState.
     *
     * @param pAddr
     *      de.beckhoff.jni.tcads.AmsAddr object with the port number and the
     *      NetId of the ADS server.
     * @param pNotification
     *      de.beckhoff.jni.tcads.AdsNotificationHeader object that contains
     *      information about the cause of the event.
     * @param hUser
     *      the events associated user.
     */
    protected void doAdsStateCallback(AmsAddr pAddr,
                                      AdsNotificationHeader pNotification,
                                      JNILong hUser) {
        processCallbackAdsState(pAddr, pNotification, hUser.getLong());
    }

    /**
     * This method adds a new listener to the list of current listeners. Each
     * listeners onEvent method is invoked on an event.
     *
     * @param listener
     *      the listener implementing the CallbackListenerAdsState interface.
     */
    public void addListenerCallbackAdsState(CallbackListenerAdsState listener) {
        mCallbackListenersAdsState.addElement(listener);
    }

    /**
     * This method removes a certain listener from the list of current
     * listeners.
     *
     * @param listener
     *      the listener to be removed.
     */
    public void
    removeListenerCallbackAdsState(CallbackListenerAdsState listener) {
        mCallbackListenersAdsState.removeElement(listener);
    }

    /**
     * This method informs all declared listeners about any events (e.g.
     * variable change events). OnEvent methods are going to be invoked.
     *
     * @param pAddr
     *      de.beckhoff.jni.tcads.AmsAddr object with the port number and the
     *      NetId of the ADS server.
     * @param pNotification
     *      de.beckhoff.jni.tcads.AdsNotificationHeader object that contains
     *      information about the cause of the event.
     * @param hUser
     *      the events associated user.
     */
    public void processCallbackAdsState(AmsAddr pAddr,
                                        AdsNotificationHeader pNotification,
                                        long hUser) {
        if (!mCallbackListenersAdsState.isEmpty()) {
            for (int i = 0; i < mCallbackListenersAdsState.size(); i++) {
                ((CallbackListenerAdsState)mCallbackListenersAdsState.elementAt(
                     i))
                    .onEvent(pAddr, pNotification, hUser);
            }
        }
    }
}