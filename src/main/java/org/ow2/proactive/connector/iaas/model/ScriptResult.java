package org.ow2.proactive.connector.iaas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;


@Getter
@AllArgsConstructor
@ToString
@Wither
@NoArgsConstructor
public class ScriptResult {

    private String instanceId;
    private String output;
    private String error;
}
