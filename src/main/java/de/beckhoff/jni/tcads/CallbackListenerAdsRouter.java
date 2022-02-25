/*
 * CallbackListenerAdsRouter.java
 */
package de.beckhoff.jni.tcads;

/**
 * Objects have to implement this interface to be addable to an
 * AdsCallbackObject as a router event listener.
 *
 * @author Beckhoff Automation
 */
public interface CallbackListenerAdsRouter {
    /**
     * If this is a declared listener to an event. The onEvent method in invoked
     * whenever the event occurs.
     * @param nReason
     *      the long representing the reason for the event occurrence.
     */
    public void onEvent(long nReason);
}