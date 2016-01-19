package jp.co.brycen.wms.spin00301search.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00301search.dto.Spin00301SearchRequest;
import jp.co.brycen.wms.spin00301search.dto.Spin00301SearchResponse;
import jp.co.brycen.wms.spin00301search.process.Spin00301SearchProcess;

@Path("wms")
public class Spin00301SearchWebService extends AbstractWebService {

	@POST
	@Path("/Spin00301Search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00301SearchResponse search(Spin00301SearchRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00301SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spin00301SearchProcess(this);
	}
}