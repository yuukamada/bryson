package jp.co.brycen.wms.spin00101search.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.spin00101search.dto.Spin00101SearchRequest;
import jp.co.brycen.wms.spin00101search.dto.Spin00101SearchResponse;
import jp.co.brycen.wms.spin00101search.process.Spin00101SearchProcess;


@Path("wms")
public class Spin00101SearchWebService extends AbstractWebService {

	@POST
	@Path("/Spin00101Search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Spin00101SearchResponse search(Spin00101SearchRequest item) throws SQLException {
		AbstractResponse abs = super.executeProcess(item);
		return (Spin00101SearchResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new Spin00101SearchProcess(this);
	}
}