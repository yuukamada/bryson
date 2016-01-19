package jp.co.brycen.wms.spin00101init.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101init.dto.Spin00101InitRequest;
import jp.co.brycen.wms.spin00101init.dto.Spin00101InitResponse;
import jp.co.brycen.wms.spin00101init.process.Spin00101InitProcess;

@Path("wms")
public class Spin00101InitWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Init")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101InitResponse search(Spin00101InitRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101InitResponse)abs;
	}


	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101InitProcess(this);
	}
}