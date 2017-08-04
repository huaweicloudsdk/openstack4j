/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample.trove;

import org.openstack4j.openstack.key.management.domain.Key;
import org.openstack4j.openstack.trove.constant.DatastoreType;
import org.openstack4j.openstack.trove.domain.Datastore;
import org.openstack4j.openstack.trove.domain.DatabaseInstance;
import org.openstack4j.openstack.trove.domain.DatabaseInstanceCreate;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(suiteName = "Trove/Instance/Sample")
public class DBInstanceSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(DBInstanceSample.class);

	String name = randomName();
	Key key = null;

	/**
	 * create a key for test
	 */
	@BeforeClass
	public void prepare() {
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("5.6.34").build();
		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name(name).datastore(datastore).build();
		DatabaseInstance instance = osclient.trove().instances().create(instanceCreate);
	}

	@Test
	public void testCreateInstance() {
		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().build();

		DatabaseInstance instance = osclient.trove().instances().create(instanceCreate);
	}

}
