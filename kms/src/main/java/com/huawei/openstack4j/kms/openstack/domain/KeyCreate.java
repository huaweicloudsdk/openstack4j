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
package com.huawei.openstack4j.kms.openstack.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class KeyCreate implements ModelEntity {


	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * key type
	 */
	@JsonProperty("key_type")
	String type;
	
	
	/**
	 * Alias of the key
	 */
	@JsonProperty("key_alias")
	String alias;
	
	/**
	 * Region where a key resides
	 */
	@JsonProperty("realm")
	String realm;
	
	/**
	 * Description of the key
	 */
	@JsonProperty("key_description")
	String description;
	
	
	/**
	 * Reserved:: Policy of the key
	 */
	@JsonProperty("key_policy")
	String policy;

	
	/**
	 * Purpose of a key (The default value is Encrypt_Decrypt, that is, 
	 * a key is used to encrypt and decrypt data.)
	 */
	@JsonProperty("key_usage")
	String usage;
	
	/**
	 * a 36 bit serial number sequence used to trace the request
	 */
	@JsonProperty("sequence")
	String sequence;
	
}
