package jp.co.brycen.wms.tmt280search.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.tmt280search.dto.Tmt280SearchRequest;
import jp.co.brycen.wms.tmt280search.dto.Tmt280SearchResponse;
import jp.co.brycen.wms.tmt280search.process.Tmt280SearchProcess;

@Path("wms")
public class Tmt280SearchWebService extends AbstractWebService {

	@POST
	@Path("/Tmt280Search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tmt280SearchResponse search(Tmt280SearchRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (Tmt280SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Tmt280SearchProcess(this);
	}
}