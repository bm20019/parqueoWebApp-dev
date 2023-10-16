/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoEspacioBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoEspacio;

/**
 *
 * @author adalberto
 */
@Path("/tipo_espacio_async")
public class TipoEspacioAsyncResource {
    @Inject
    TipoEspacioBean teBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public CompletableFuture<List<TipoEspacio>>  finRange(
            @QueryParam(value = "fist")
            @DefaultValue(value = "0") int fist,
            @QueryParam(value = "page_size") @DefaultValue(value = "10") int pageSize) {
        //return teBean.findRange(fist, pageSize);
        return CompletableFuture.supplyAsync(()-> teBean.findRageSlow(fist, pageSize));
    }
}
