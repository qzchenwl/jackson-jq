package net.thisptr.jackson.jq.internal.functions;

import com.fasterxml.jackson.databind.JsonNode;

import net.thisptr.jackson.jq.internal.BuiltinFunction;
import net.thisptr.jackson.jq.internal.JsonPredicateFunction;
import net.thisptr.jackson.jq.internal.missing.Predicate;

@BuiltinFunction("isnan/0")
public class IsNanFunction extends JsonPredicateFunction {
	public IsNanFunction() {
		super(IsNanFunction.test);
	}

	private static Predicate<JsonNode> test = new Predicate<JsonNode>() {
		@Override
		public boolean test(JsonNode value) {
			if ((value.isDouble() || value.isFloat()) && Double.isNaN(value.asDouble()))
				return true;
			return false;
		}
	};
}
