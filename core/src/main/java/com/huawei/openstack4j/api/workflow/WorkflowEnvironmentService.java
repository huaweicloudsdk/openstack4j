/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.workflow;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.workflow.WorkflowEnvironment;

/**
 * Service that provides CRUD operations for workflow environments.
 *
 * @author Renat Akhmerov
 */
public interface WorkflowEnvironmentService extends RestService {
    /**
     * List all workflow environments with details.
     *
     * @return List of workflow environments.
     */
    List<? extends WorkflowEnvironment> list();

    /**
     * Create a new workflow environment.
     *
     * @param workflowEnvironment Workflow environment to create.
     * @return Created workflow environment.
     */
    WorkflowEnvironment create(WorkflowEnvironment workflowEnvironment);

    /**
     * Get workflow environment by its ID.
     *
     * @param id Workflow environment ID.
     * @return Workflow environment.
     */
    WorkflowEnvironment get(String id);

    /**
     * Delete workflow environment by its ID.
     *
     * @param id Workflow environment ID.
     * @return HTTP response from the server.
     */
    HttpResponse delete(String id);
}