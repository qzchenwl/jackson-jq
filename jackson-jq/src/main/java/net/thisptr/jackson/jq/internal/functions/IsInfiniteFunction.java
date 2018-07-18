package net.thisptr.jackson.jq.internal.functions;

import com.fasterxml.jackson.databind.JsonNode;
import net.thisptr.jackson.jq.internal.BuiltinFunction;
import net.thisptr.jackson.jq.internal.JsonPredicateFunction;
import net.thisptr.jackson.jq.internal.missing.Predicate;

@BuiltinFunction("isinfinite/0")
public class IsInfiniteFunction extends JsonPredicateFunction {
	public IsInfiniteFunction() {
		super(new Predicate<JsonNode>() {
			@Override
			public boolean test(JsonNode jsonNode) {
				return IsInfiniteFunction.test(jsonNode);
			}
		});
	}

	private static boolean test(final JsonNode value) {
		if ((value.isDouble() || value.isFloat()) && Double.isInfinite(value.asDouble()))
			return true;
		return false;
	}
}
