package jp.co.brycen.wms.spin00101regist.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101regist.dto.Spin00101RegistRequest;
import jp.co.brycen.wms.spin00101regist.dto.Spin00101RegistResponse;
import jp.co.brycen.wms.spin00101regist.process.Spin00101RegistProcess;

@Path("wms")
public class Spin00101RegistWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Regist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101RegistResponse search(Spin00101RegistRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101RegistResponse)abs;
	}


	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101RegistProcess(this);
	}
}