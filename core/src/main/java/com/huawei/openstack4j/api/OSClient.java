/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
package com.huawei.openstack4j.api;

import java.util.Map;
import java.util.Set;

import com.huawei.openstack4j.api.artifact.ArtifactService;
import com.huawei.openstack4j.api.barbican.BarbicanService;
import com.huawei.openstack4j.api.cloudeye.CloudEyeService;
import com.huawei.openstack4j.api.compute.ComputeService;
import com.huawei.openstack4j.api.dns.v2.DNSService;
import com.huawei.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import com.huawei.openstack4j.api.gbp.GbpService;
import com.huawei.openstack4j.api.heat.HeatService;
import com.huawei.openstack4j.api.image.ImageService;
import com.huawei.openstack4j.api.loadbalance.ELBService;
import com.huawei.openstack4j.api.magnum.MagnumService;
import com.huawei.openstack4j.api.manila.ShareService;
import com.huawei.openstack4j.api.murano.v1.AppCatalogService;
import com.huawei.openstack4j.api.networking.NetworkingService;
import com.huawei.openstack4j.api.sahara.SaharaService;
import com.huawei.openstack4j.api.scaling.AutoScalingService;
import com.huawei.openstack4j.api.senlin.SenlinService;
import com.huawei.openstack4j.api.storage.BlockStorageService;
import com.huawei.openstack4j.api.storage.ObjectStorageService;
import com.huawei.openstack4j.api.tacker.TackerService;
import com.huawei.openstack4j.api.telemetry.TelemetryService;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.api.workflow.WorkflowService;
import com.huawei.openstack4j.model.identity.v2.Access;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSServices;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.CloudTraceV1Service;
import com.huawei.openstack4j.openstack.cloud.trace.v2.internal.CloudTraceV2Service;
import com.huawei.openstack4j.openstack.database.internal.DatabaseServices;
import com.huawei.openstack4j.openstack.key.management.internal.KeyManagementService;
import com.huawei.openstack4j.openstack.maas.internal.MaaSService;
import com.huawei.openstack4j.openstack.message.notification.internal.NotificationService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueService;
import com.huawei.openstack4j.openstack.trove.internal.TroveService;

/**
 * A client which has been identified. Any calls spawned from this session will
 * automatically utilize the original authentication that was successfully
 * validated and authorized
 *
 * @author Jeremy Unruh
 */
public interface OSClient<T extends OSClient<T>> {

	/**
	 * Specifies the region that should be used for further invocations with
	 * this client. If the region is invalid or doesn't exists execution errors
	 * will occur when invoking API calls and a
	 * {@link RegionEndpointNotFoundException} will be thrown
	 *
	 * @param region the region to use
	 * @return OSClient for method chaining
	 */
	T useRegion(String region);

	/**
	 * Removes the current region making all calls no longer resolving to region
	 * (if originally set otherwise no-op)
	 *
	 * @return OSClient for method chaining
	 */
	T removeRegion();

	/**
	 * Changes the Perspective for the current Session (Client)
	 *
	 * @param perspective the new perspective
	 * @return OSClient for method chaining
	 */
	T perspective(Facing perspective);

	/**
	 * Passes the Headers for the current Session(Client)
	 *
	 * @param headers the headers to use for keystone tokenless
	 * @return OSClient for method chaining
	 */
	T headers(Map<String, ? extends Object> headers);

	/**
	 * Gets the supported services. A set of ServiceTypes will be returned
	 * identifying the OpenStack services installed and supported
	 *
	 * @return the supported services
	 */
	Set<ServiceType> getSupportedServices();

	/**
	 * Determines if the Compute (Nova) service is supported
	 *
	 * @return true, if supports compute
	 */
	boolean supportsCompute();

	/**
	 * Determines if the Identity (Keystone) service is supported
	 *
	 * @return true, if supports identity
	 */
	boolean supportsIdentity();

	/**
	 * Determines if the Network (Neutron) service is supported
	 *
	 * @return true, if supports network
	 */
	boolean supportsNetwork();

	/**
	 * Determines if the Image (Glance) service is supported
	 *
	 * @return true, if supports image
	 */
	boolean supportsImage();

	/**
	 * Determines if the Orchestration (Heat) service is supported
	 *
	 * @return true if supports Heat
	 */
	boolean supportsHeat();

	/**
	 * Determines if the App Catalog (Murano) service is supported
	 *
	 * @return true if supports Murano
	 */
	boolean supportsMurano();

	/**
	 * Determines if the Block Storage (Cinder) service is supported
	 *
	 * @return true if supports Block Storage
	 */
	boolean supportsBlockStorage();

	/**
	 * Determines if the Object Storage (Swift) service is supported
	 *
	 * @return true if supports Object Storage
	 */
	boolean supportsObjectStorage();

	/**
	 * Determines if the Telemetry (Ceilometer) service is supported
	 *
	 * @return true if supports Telemetry
	 */
	boolean supportsTelemetry();

	/**
	 * Determines if the Shared File Systems (Manila) service is supported
	 *
	 * @return true if supports Shared File Systems
	 */
	boolean supportsShare();

	/**
	 * Gets the current endpoint of the Identity service
	 *
	 * @return the endpoint
	 */
	String getEndpoint();

	/**
	 * Returns the Compute Service API
	 *
	 * @return the compute service
	 */
	ComputeService compute();

	/**
	 * Returns the Networking Service API
	 *
	 * @return the networking service
	 */
	NetworkingService networking();

	/**
	 * Returns the Artifact Service API
	 *
	 * @return the artifact service
	 */
	ArtifactService artifact();

	/**
	 * Returns the Tacker Service API
	 *
	 * @return the tacker service
	 */
	TackerService tacker();

	/**
	 * Returns the Block Storage Service API
	 *
	 * @return the block storage service
	 */
	BlockStorageService blockStorage();

	/**
	 * Returns the Object Storage Service API
	 *
	 * @return the object storage service
	 */
	ObjectStorageService objectStorage();

	/**
	 * Returns the Image Service API
	 *
	 * @return the image service
	 */
	ImageService images();

	/**
	 * Returns the Image V2 Service API
	 * @return the image v2 service
	 */
	com.huawei.openstack4j.api.image.v2.ImageService imagesV2();

	/**
	 * Returns the Telemetry Service API
	 *
	 * @return the telemetry service
	 */
	TelemetryService telemetry();

	/**
	 * Returns the Shared File Systems API
	 *
	 * @return the share service
	 */
	ShareService share();

	/**
	 * Returns the Heat Service API
	 *
	 * @return the Heat service
	 */
	HeatService heat();

	/**
	 * Returns the Murano Service API
	 *
	 * @return the Murano service
	 */
	AppCatalogService murano();

	/**
	 * Returns the Sahara Service API
	 *
	 * @return the Sahara service
	 */
	SaharaService sahara();

	/**
	 * Returns the Workflow Service API
	 *
	 * @return the Workflow service
	 */
	WorkflowService workflow();

	/**
	 * Returns the Magnum Service API
	 * 
	 * @return the Magnum Service
	 */
	MagnumService magnum();

	/**
	 * OpenStack4j Client which authenticates against version V2
	 */
	public interface OSClientV2 extends OSClient<OSClient.OSClientV2> {

		/**
		 * Returns the Identity V2 Access object assigned during authentication
		 * 
		 * @return the Access object
		 */
		Access getAccess();

		/**
		 * Returns the Identity Service API V2
		 * 
		 * @return the identity service version 2
		 */
		com.huawei.openstack4j.api.identity.v2.IdentityService identity();

	}

	/**
	 * OpenStack4j Client which authenticates against version V3
	 */
	public interface OSClientV3 extends OSClient<OSClient.OSClientV3> {

		/**
		 * Gets the token that was assigned during authorization
		 *
		 * @return the authentication token
		 */
		Token getToken();

		/**
		 * Returns the Identity Service API V3
		 *
		 * @return the identity service version 3
		 */
		com.huawei.openstack4j.api.identity.v3.IdentityService identity();

		
		/**
		 * get the Auto Scaling service 
		 * @return the OTC AutoScaling Service
		 */
		AutoScalingService autoScaling();
		
		/**
		 * get the Elastic Load Balance service 
		 * @return the OTC Elastic Load Balance service
		 */
		ELBService loadBalancer();
		
		
		/**
		 * get the Key Management service 
		 * @return the OTC Key Management service instance
 		 */
		KeyManagementService keyManagement();
		
		/**
		 * get the Cloud Trace V1 service 
		 * @return the OTC Cloud Trace V1 service instance
		 */
		CloudTraceV1Service cloudTraceV1();
		
		/**
		 * get the Cloud Trace V2 service 
		 * @return the OTC Cloud Trace V2 service instance
		 */
		CloudTraceV2Service cloudTraceV2();
		
		
		/**
		 * get the AntiDDos service 
		 * @return the OTC AntiDDos service instance
		 */
		AntiDDoSServices antiDDoS();
		
		/**
		 * get the Simple Message Notification service 
		 * @return the OTC Simple Message Notification service instance
		 */
		NotificationService notification();
		
		/**
		 * get the Distributed Message service 
		 * @return the OTC Distributed Message service instance
		 */
		MessageQueueService messageQueue();
		
		/**
		 * get the MaaS service 
		 * @return the OTC MaaS service instance
		 */
		MaaSService maas();
		
		/**
		 * get the Database service 
		 * @return the OTC {@link DatabaseServices} instance
		 */
		DatabaseServices database();
	}

	/**
	 * Returns the Gbp Service API
	 * 
	 * @return the Gbp service
	 */
	GbpService gbp();

	/**
	 * Returns the Senlin Service API
	 *
	 * @return the Senlin service
	 */
	SenlinService senlin();

	/**
	 *  Returns the Trove Service API
	 *
	 * @return the Trove service
	 */
	TroveService trove();

	/**
	 * Returns the Barbican Service API
	 *
	 * @return the Barbican service
	 */
	BarbicanService barbican();

	/**
	 * Returns the DNS Service API
	 *
	 * @return the DNS service
	 */
	DNSService dns();

	/**
	 * Returns the CloudEye Service API
	 * @return the CloudEye service
	 */
	CloudEyeService cloudEye();

}