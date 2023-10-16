package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoEspacioBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoEspacio;

/**
 *
 * @author adalberto
 */
@Path("tipo_espacio")
public class TipoEspacioResource {

    @Inject
    TipoEspacioBean teBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoEspacio> finRange(
            @QueryParam(value = "fist")
            @DefaultValue(value = "0") int fist,
            @QueryParam(value = "page_size") @DefaultValue(value = "10") int pageSize
    ) {
        return teBean.findRange(fist, pageSize);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response findById(@PathParam(value="id") Integer idTipoEspacio) {
        if (idTipoEspacio !=null) {
                TipoEspacio encontrado = teBean.findById(idTipoEspacio);
                if(encontrado !=null){
                    return Response.status(Response.Status.OK).entity(encontrado).build();
                }
                return Response.status(Response.Status.NOT_FOUND).header("not-found", idTipoEspacio).build();
        }
        return Response.status(422).header("missing-parameters", "id").build();
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoEspacio registro, @Context UriInfo info) {
        if (registro !=null && registro.getIdTipoEspacio() !=null && registro.getNombre() != null) {
            try {
                teBean.create(registro);
                URI requestUri = info.getRequestUri();
                return Response.status(Response.Status.CREATED)
                        .header("location", requestUri.toString() + "/" + registro.getIdTipoEspacio())
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
            }
            return Response.status(Response.Status.BAD_REQUEST).header("created-exception", registro.toString()).build();
        }
        return Response.status(422).header("missing-parameter", "id").build();
    }
}
