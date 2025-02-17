/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.model.net.ssh.registry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.impl.AbstractContextDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.net.ssh.SSHImplementation;
import org.jkiss.dbeaver.registry.RegistryConstants;
import org.jkiss.utils.CommonUtils;

/**
 * SSHImplementationDescriptor
 */
public class SSHImplementationDescriptor extends AbstractContextDescriptor
{
    static final String EXTENSION_ID = "org.jkiss.dbeaver.net.ssh"; //$NON-NLS-1$

    private final AbstractDescriptor.ObjectType implClass;
    private final String id;
    private final String label;
    private final boolean supportsJumpServer;

    SSHImplementationDescriptor(
        IConfigurationElement config)
    {
        super(config);

        this.id = config.getAttribute(RegistryConstants.ATTR_ID);
        this.label = config.getAttribute(RegistryConstants.ATTR_LABEL);
        this.implClass = new AbstractDescriptor.ObjectType(config.getAttribute(RegistryConstants.ATTR_CLASS));
        this.supportsJumpServer = CommonUtils.getBoolean(config.getAttribute("supportsJumpServer"));
    }

    public ObjectType getImplClass() {
        return implClass;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSupportsJumpServer() {
        return supportsJumpServer;
    }

    public SSHImplementation createImplementation()
        throws DBException
    {
        return implClass.createInstance(SSHImplementation.class);
    }

}
