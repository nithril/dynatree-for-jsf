/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nigajuan.dynatree.context;

import org.nigajuan.dynatree.model.DTreeNode;

import java.util.List;

public class DTRequestContext {

    private List<DTreeNode> nodes;

    private static ThreadLocal<DTRequestContext> instance = new ThreadLocal<DTRequestContext>(){
        @Override
        protected DTRequestContext initialValue() {
            return new DTRequestContext();
        }
    };

    public static DTRequestContext getCurrentInstance() {
        return instance.get();
    }

    public List<DTreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<DTreeNode> nodes) {
        this.nodes = nodes;
    }
}
