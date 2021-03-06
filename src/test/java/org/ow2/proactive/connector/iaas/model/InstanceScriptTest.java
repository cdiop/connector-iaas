package org.ow2.proactive.connector.iaas.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;
import org.ow2.proactive.connector.iaas.fixtures.CredentialsFixtures;

import jersey.repackaged.com.google.common.collect.Sets;


public class InstanceScriptTest {
    @Test
    public void testEmptyConstructor() {
        InstanceScript instanceScript = new InstanceScript();
        assertThat(instanceScript.getScripts(), is(nullValue()));
    }

    @Test
    public void testConstructor() {
        String[] scripts = new String[] { "script 1", "script 2" };
        InstanceScript instanceScript = new InstanceScript(
            CredentialsFixtures.getCredentials("username", "password"), scripts);
        assertThat(instanceScript.getScripts(), is(scripts));
        assertThat(instanceScript.getCredentials().getUsername(), is("username"));
        assertThat(instanceScript.getCredentials().getPassword(), is("password"));

    }

    @Test
    public void testEqualsAndHashcode() {
        String[] scripts = new String[] { "script 1", "script 2" };

        InstanceScript instanceScript1 = new InstanceScript(
            CredentialsFixtures.getCredentials("username", "password"), scripts);
        InstanceScript instanceScript2 = new InstanceScript(
            CredentialsFixtures.getCredentials("username", "password"), scripts);

        Set<InstanceScript> instanceScripts = Sets.newHashSet(instanceScript1, instanceScript2);

        assertThat(instanceScripts.size(), is(2));
        assertThat(instanceScript1.equals(instanceScript2), is(false));
    }

}
