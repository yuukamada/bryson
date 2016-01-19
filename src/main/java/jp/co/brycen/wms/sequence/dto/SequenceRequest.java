package jp.co.brycen.wms.sequence.dto;

import jp.co.brycen.common.dto.request.AbstractRequest;

/**
 * 概要：シーケンス取得
 */
public class SequenceRequest extends AbstractRequest{

	// シーケンス取得条件
	public SequenceConditionDto sequenceCondition = new SequenceConditionDto();
}
