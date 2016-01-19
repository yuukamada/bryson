package jp.co.brycen.wms.spin00101delete.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101delete.dto.Spin00101DeleteRequest;
import jp.co.brycen.wms.spin00101delete.dto.Spin00101DeleteResponse;
import jp.co.brycen.wms.spin00101delete.process.Spin00101DeleteProcess;

@Path("wms")
public class Spin00101DeleteWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101DeleteResponse search(Spin00101DeleteRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101DeleteResponse)abs;
	}


	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101DeleteProcess(this);
	}
}