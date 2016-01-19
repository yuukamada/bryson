package jp.co.brycen.wms.sequence.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.wms.sequence.dto.SequenceRequest;
import jp.co.brycen.wms.sequence.dto.SequenceResponse;
import jp.co.brycen.wms.sequence.process.SequenceProcess;

@Path("wms")
public class SequenceWebService extends AbstractWebService {

	@POST
	@Path("/Sequence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SequenceResponse search(SequenceRequest item) {
		AbstractResponse abs = super.executeProcess(item);
		return (SequenceResponse)abs;
	}

	@Override
	protected AbstractProcess getProcess() {
		return new SequenceProcess(this);
	}
}