package org.ow2.proactive.connector.iaas.app.exception;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;

import org.junit.Test;


public class ClientErrorExceptionMapperTest {

    @Test
    public void testToResponseBadRequestException() {
        Exception e = new Exception("Original Exception cause");
        e.setStackTrace(new StackTraceElement[] {
                new StackTraceElement("declaringClass", "methodName", "fileName", 123) });
        Response response = new ClientErrorExceptionMapper()
                .toResponse(new BadRequestException("Error message", e));

        assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));

        String errorEntity = (String) response.getEntity();

        assertThat(errorEntity.contains("Error message"), is(true));

    }

    @Test
    public void testToResponseNotFoundException() {
        Exception e = new Exception("Original Exception cause");
        e.setStackTrace(new StackTraceElement[] {
                new StackTraceElement("declaringClass", "methodName", "fileName", 123) });
        Response response = new ClientErrorExceptionMapper()
                .toResponse(new NotFoundException("Error message", e));

        assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));

        String errorEntity = (String) response.getEntity();

        assertThat(errorEntity.contains("Error message"), is(true));

    }

    @Test
    public void testToResponseNotSupportedException() {
        Exception e = new Exception("Original Exception cause");
        e.setStackTrace(new StackTraceElement[] {
                new StackTraceElement("declaringClass", "methodName", "fileName", 123) });
        Response response = new ClientErrorExceptionMapper()
                .toResponse(new NotSupportedException("Error message", e));

        assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));

        String errorEntity = (String) response.getEntity();

        assertThat(errorEntity.contains("Error message"), is(true));

    }

}
