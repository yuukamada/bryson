package jp.co.brycen.wms.spin00201init.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00201init.dto.Spin00201InitRequest;
import jp.co.brycen.wms.spin00201init.dto.Spin00201InitResponse;

@Path("wms")
public class Spin00201InitWebService extends AbstractWebService {

	@POST
	@Path("/Spin00201Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00201InitResponse search(Spin00201InitRequest item) throws SQLException {
		return new Spin00201InitResponse();
	}


	@Override
	protected AbstractProcess getProcess() {
		return null;
	}
}