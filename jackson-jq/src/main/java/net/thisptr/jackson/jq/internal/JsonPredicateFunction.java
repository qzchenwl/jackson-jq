package net.thisptr.jackson.jq.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import net.thisptr.jackson.jq.Function;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.exception.JsonQueryException;
import net.thisptr.jackson.jq.internal.missing.Predicate;

import java.util.Collections;
import java.util.List;

public class JsonPredicateFunction implements Function {
	private Predicate<JsonNode> predicate;

	public JsonPredicateFunction(final Predicate<JsonNode> predicate) {
		this.predicate = predicate;
	}

	@Override
	public List<JsonNode> apply(Scope scope, List<JsonQuery> args, JsonNode in) throws JsonQueryException {
		return Collections.singletonList((JsonNode) BooleanNode.valueOf(predicate.test(in)));
	}
}