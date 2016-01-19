package jp.co.brycen.wms.spin00301init.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00301init.dto.Spin00301InitRequest;
import jp.co.brycen.wms.spin00301init.dto.Spin00301InitResponse;
import jp.co.brycen.wms.spin00301init.dto.Spin00301InitRequest;
import jp.co.brycen.wms.spin00301init.dto.Spin00301InitResponse;

@Path("wms")
public class Spin00301InitWebService extends AbstractWebService {

	@POST
	@Path("/Spin00301Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00301InitResponse search(Spin00301InitRequest item) throws SQLException {
		return new Spin00301InitResponse();
	}


	@Override
	protected AbstractProcess getProcess() {
		return null;
	}
}