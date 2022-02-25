/*
 * CallbackListenerAdsState.java
 */
package de.beckhoff.jni.tcads;

/**
 * Objects have to implement this interface to be addable to an
 * AdsCallbackObject as an event listener.
 *
 * @author Beckhoff Automation
 */
public interface CallbackListenerAdsState {
    /**
     * If this is a declared listener to an event. The onEvent method in invoked
     * whenever the event occurs.
     * @param pAddr
     *      de.beckhoff.jni.tcads.AmsAddr object with the port number and the
     *      NetId of the ADS server.
     * @param pNotification
     *      de.beckhoff.jni.tcads.AdsNotificationHeader object that contains
     *      information about the cause of the event.
     * @param hUser
     *      the events associated user.
     */
    public void onEvent(AmsAddr pAddr, AdsNotificationHeader pNotification,
                        long hUser);
}