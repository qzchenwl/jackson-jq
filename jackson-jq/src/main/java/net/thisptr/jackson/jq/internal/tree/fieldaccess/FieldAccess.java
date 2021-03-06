package net.thisptr.jackson.jq.internal.tree.fieldaccess;

import java.util.ArrayList;
import java.util.List;

import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.exception.JsonQueryException;
import net.thisptr.jackson.jq.internal.tree.fieldaccess.resolved.ResolvedFieldAccess;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class FieldAccess extends JsonQuery {
	protected JsonQuery target;
	protected boolean permissive;

	public FieldAccess(final JsonQuery target, final boolean permissive) {
		this.target = target;
		this.permissive = permissive;
	}

	public static class ResolvedPath {
		public JsonQuery target;
		public List<ResolvedFieldAccess> path;

		public ResolvedPath(final JsonQuery target) {
			this.target = target;
			this.path = new ArrayList<>();
		}

		public ResolvedPath access(final ResolvedFieldAccess path) {
			this.path.add(path);
			return this;
		}
	}

	@Override
	public List<JsonNode> apply(final Scope scope, final JsonNode in) throws JsonQueryException {
		final List<JsonNode> out = new ArrayList<>();
		final ResolvedFieldAccess resolvedFieldAccess = resolveFieldAccess(scope, in);
		for (final JsonNode i : target.apply(scope, in))
			out.addAll(resolvedFieldAccess.apply(scope, i));
		return out;
	}

	public abstract ResolvedFieldAccess resolveFieldAccess(final Scope scope, final JsonNode in) throws JsonQueryException;

	public ResolvedPath resolvePath(final Scope scope, final JsonNode in) throws JsonQueryException {
		final ResolvedPath resolvedPath;
		if (target instanceof FieldAccess) {
			resolvedPath = ((FieldAccess) target).resolvePath(scope, in);
		} else {
			resolvedPath = new ResolvedPath(target);
		}
		return resolvedPath.access(resolveFieldAccess(scope, in));
	}
}
