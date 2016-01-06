package org.ow2.proactive.iaas.connector.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ow2.proactive.iaas.connector.cloud.CloudManager;
import org.ow2.proactive.iaas.connector.fixtures.InfrastructureFixture;
import org.ow2.proactive.iaas.connector.fixtures.InstanceScriptFixture;
import org.ow2.proactive.iaas.connector.model.Infrastructure;
import org.ow2.proactive.iaas.connector.model.InstanceScript;
import org.ow2.proactive.iaas.connector.model.ScriptResult;


public class InstanceScriptServiceTest {

    @InjectMocks
    private InstanceScriptService instanceScriptService;

    @Mock
    private InfrastructureService infrastructureService;

    @Mock
    private CloudManager cloudManager;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteScriptOnInstance() {
        Infrastructure infrastructure = InfrastructureFixture.getInfrastructure("id-aws", "aws", "endPoint",
                "userName", "credential");
        when(infrastructureService.getInfrastructurebyName(infrastructure.getName()))
                .thenReturn(infrastructure);

        InstanceScript instanceScript = InstanceScriptFixture.simpleInstanceScriptNoscripts("id");

        when(cloudManager.executeScript(infrastructure, instanceScript))
                .thenReturn(new ScriptResult("output", "error"));

        ScriptResult scriptResult = instanceScriptService.executeScriptOnInstance(infrastructure.getName(),
                instanceScript);

        assertThat(scriptResult.getOutput(), is(scriptResult.getOutput()));
        assertThat(scriptResult.getError(), is(scriptResult.getError()));

        verify(cloudManager, times(1)).executeScript(infrastructure, instanceScript);

    }

}