package jp.co.brycen.wms.spin00201search.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00201search.dto.Spin00201SearchRequest;
import jp.co.brycen.wms.spin00201search.dto.Spin00201SearchResponse;
import jp.co.brycen.wms.spin00201search.process.Spin00201SearchProcess;

@Path("wms")
public class Spin00201SearchWebService extends AbstractWebService {

	@POST
	@Path("/Spin00201Search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00201SearchResponse search(Spin00201SearchRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00201SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spin00201SearchProcess(this);
	}
}