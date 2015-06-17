package com.citytechinc.aem.prosper.mocks.resource

import com.citytechinc.aem.prosper.mocks.adapter.ProsperAdapterManager
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.SyntheticResource

class MockNonExistingResource extends SyntheticResource {

    private final ProsperAdapterManager adapterManager

    MockNonExistingResource(ResourceResolver resourceResolver, String path, ProsperAdapterManager adapterManager) {
        super(resourceResolver, path, "sling:nonexisting")

        this.adapterManager = adapterManager
    }

    @Override
    def <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
        def result = adapterManager.adapt(this, type)

        // specifically check for null so we don't incorrectly check empty collections
        if (result == null) {
            result = super.adaptTo(type)
        }

        result
    }
}
