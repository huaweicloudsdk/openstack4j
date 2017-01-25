package org.openstack4j.model.artifact;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.artifact.builder.ToscaTemplatesArtifactBuilder;
import org.openstack4j.model.common.BasicResource;

/**
 * A Glare Tosca Templates Artifact
 *
 * @author Pavan Vadavi
 */
public interface ToscaTemplatesArtifact extends Artifact, BasicResource, Buildable<ToscaTemplatesArtifactBuilder> {

    Template getTemplate();

    String getTemplateFormat();

}
