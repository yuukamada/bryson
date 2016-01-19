package jp.co.brycen.wms.spin00101update.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101update.dto.Spin00101UpdateRequest;
import jp.co.brycen.wms.spin00101update.dto.Spin00101UpdateResponse;
import jp.co.brycen.wms.spin00101update.process.Spin00101UpdateProcess;

@Path("wms")
public class Spin00101UpdateWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101UpdateResponse search(Spin00101UpdateRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101UpdateResponse)abs;
	}


	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101UpdateProcess(this);
	}
}