/**
 * Copyright 2005-2020 Talend
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * https://restlet.talend.com/
 * 
 * Restlet is a registered trademark of Talend S.A.
 */

package org.restlet.test.ext.odata.deepexpand.model;

import java.util.Date;
import java.util.List;

import org.restlet.test.ext.odata.deepexpand.model.Group;
import org.restlet.test.ext.odata.deepexpand.model.Multilingual;
import org.restlet.test.ext.odata.deepexpand.model.Registration;

/**
 * Generated by the generator tool for the OData extension for the Restlet
 * framework.<br>
 * 
 * @see <a
 *      href="http://praktiki.metal.ntua.gr/CoopOData/CoopOData.svc/$metadata">Metadata
 *      of the target OData service</a>
 * 
 */
public class Invitation {

    private Date date;

    private int id;

    private Tracking tracking;

    private Group group;

    private List<Registration> recepients;

    private Registration sender;

    private Multilingual text;

    /**
     * Constructor without parameter.
     * 
     */
    public Invitation() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            The identifiant value of the entity.
     */
    public Invitation(int id) {
        this();
        this.id = id;
    }

    /**
     * Returns the value of the "date" attribute.
     * 
     * @return The value of the "date" attribute.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the value of the "id" attribute.
     * 
     * @return The value of the "id" attribute.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the value of the "tracking" attribute.
     * 
     * @return The value of the "tracking" attribute.
     */
    public Tracking getTracking() {
        return tracking;
    }

    /**
     * Returns the value of the "group" attribute.
     * 
     * @return The value of the "group" attribute.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Returns the value of the "recepients" attribute.
     * 
     * @return The value of the "recepients" attribute.
     */
    public List<Registration> getRecepients() {
        return recepients;
    }

    /**
     * Returns the value of the "sender" attribute.
     * 
     * @return The value of the "sender" attribute.
     */
    public Registration getSender() {
        return sender;
    }

    /**
     * Returns the value of the "text" attribute.
     * 
     * @return The value of the "text" attribute.
     */
    public Multilingual getText() {
        return text;
    }

    /**
     * Sets the value of the "date" attribute.
     * 
     * @param date
     *            The value of the "date" attribute.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the value of the "id" attribute.
     * 
     * @param id
     *            The value of the "id" attribute.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the value of the "tracking" attribute.
     * 
     * @param tracking
     *            The value of the "tracking" attribute.
     */
    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    /**
     * Sets the value of the "group" attribute.
     * 
     * @param group
     *            " The value of the "group" attribute.
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Sets the value of the "recepients" attribute.
     * 
     * @param recepients
     *            " The value of the "recepients" attribute.
     */
    public void setRecepients(List<Registration> recepients) {
        this.recepients = recepients;
    }

    /**
     * Sets the value of the "sender" attribute.
     * 
     * @param sender
     *            " The value of the "sender" attribute.
     */
    public void setSender(Registration sender) {
        this.sender = sender;
    }

    /**
     * Sets the value of the "text" attribute.
     * 
     * @param text
     *            " The value of the "text" attribute.
     */
    public void setText(Multilingual text) {
        this.text = text;
    }

}