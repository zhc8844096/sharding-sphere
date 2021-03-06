/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler;

import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.ddl.DDLStatement;
import io.shardingsphere.core.parsing.parser.token.IndexToken;
import io.shardingsphere.core.util.SQLUtil;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Multiple index name extract handler.
 * 
 * @author duhongjun
 */
public final class IndexesNameExtractHandler implements ASTExtractHandler {
    
    @Override
    public void extract(final ParserRuleContext ancestorNode, final SQLStatement statement) {
        DDLStatement ddlStatement = (DDLStatement) statement;
        String tableName = ddlStatement.getTables().isEmpty() ? "" : ddlStatement.getTables().getSingleTableName();
        for (ParserRuleContext each : ASTUtils.getAllDescendantNodes(ancestorNode, RuleName.INDEX_NAME)) {
            statement.getSQLTokens().add(new IndexToken(each.getStop().getStartIndex(), SQLUtil.getNameWithoutSchema(each.getText()), tableName));
        }
    }
}
