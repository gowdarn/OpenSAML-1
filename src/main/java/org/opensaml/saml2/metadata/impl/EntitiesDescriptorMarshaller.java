/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml2.metadata.impl;

import org.opensaml.Configuration;
import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.common.CacheableSAMLObject;
import org.opensaml.saml2.common.TimeBoundSAMLObject;
import org.opensaml.saml2.metadata.EntitiesDescriptor;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * A thread safe Marshaller for {@link org.opensaml.saml2.metadata.EntitiesDescriptor} objects.
 */
public class EntitiesDescriptorMarshaller extends AbstractSAMLObjectMarshaller {

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(EntitiesDescriptorMarshaller.class);

    /** Constructor. */
    public EntitiesDescriptorMarshaller() {
        super(SAMLConstants.SAML20MD_NS, EntitiesDescriptor.DEFAULT_ELEMENT_LOCAL_NAME);
    }

    /**
     * Constructor.
     * 
     * @param targetNamespaceURI the namespace URI of either the schema type QName or element QName of the elements this
     *            marshaller operates on
     * @param targetLocalName the local name of either the schema type QName or element QName of the elements this
     *            marshaller operates on
     */
    protected EntitiesDescriptorMarshaller(String targetNamespaceURI, String targetLocalName) {
        super(targetNamespaceURI, targetLocalName);
    }

    /** {@inheritDoc} */
    protected void marshallAttributes(XMLObject samlElement, Element domElement) {

        EntitiesDescriptor entitiesDescriptor = (EntitiesDescriptor) samlElement;

        // Set the ID attribute
        if (entitiesDescriptor.getID() != null) {
            log.debug("Writing ID attribute to EntitiesDescriptor DOM element.");
            domElement.setAttributeNS(null, EntitiesDescriptor.ID_ATTRIB_NAME, entitiesDescriptor.getID());
            domElement.setIdAttributeNS(null, EntitiesDescriptor.ID_ATTRIB_NAME, true);
        }

        // Set the validUntil attribute
        if (entitiesDescriptor.getValidUntil() != null) {
            log.debug("Writting validUntil attribute to EntitiesDescriptor DOM element");
            String validUntilStr = Configuration.getSAMLDateFormatter().print(entitiesDescriptor.getValidUntil());
            domElement.setAttributeNS(null, TimeBoundSAMLObject.VALID_UNTIL_ATTRIB_NAME, validUntilStr);
        }

        // Set the cacheDuration attribute
        if (entitiesDescriptor.getCacheDuration() != null) {
            log.debug("Writting cacheDuration attribute to EntitiesDescriptor DOM element");
            String cacheDuration = XMLHelper.longToDuration(entitiesDescriptor.getCacheDuration());
            domElement.setAttributeNS(null, CacheableSAMLObject.CACHE_DURATION_ATTRIB_NAME, cacheDuration);
        }

        // Set the Name attribute
        if (entitiesDescriptor.getName() != null) {
            log.debug("Writting Name attribute to EntitiesDescriptor DOM element");
            domElement.setAttributeNS(null, EntitiesDescriptor.NAME_ATTRIB_NAME, entitiesDescriptor.getName());
        }
    }
}