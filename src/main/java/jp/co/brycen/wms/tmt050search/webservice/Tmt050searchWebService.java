package jp.co.brycen.wms.tmt050search.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchRequest;
import jp.co.brycen.wms.tmt050search.dto.Tmt050SearchResponse;
import jp.co.brycen.wms.tmt050search.process.Tmt050SearchProcess;

@Path("wms")
public class Tmt050searchWebService extends AbstractWebService {

	@POST
	@Path("/tmt050search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tmt050SearchResponse search(Tmt050SearchRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Tmt050SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Tmt050SearchProcess(this);
	}
}