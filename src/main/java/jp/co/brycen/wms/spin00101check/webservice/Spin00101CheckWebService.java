package jp.co.brycen.wms.spin00101check.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckRequest;
import jp.co.brycen.wms.spin00101check.dto.Spin00101CheckResponse;
import jp.co.brycen.wms.spin00101check.process.Spin00101CheckProcess;

@Path("wms")
public class Spin00101CheckWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101CheckResponse search(Spin00101CheckRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101CheckResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101CheckProcess(this);
	}
}