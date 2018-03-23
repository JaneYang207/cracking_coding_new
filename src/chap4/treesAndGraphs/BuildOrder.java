package chap4.treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4.7 Build order of projects
 */
public class BuildOrder {

  public static void main(String[] args) {
    String[] projectNames = {"a", "b", "c", "d", "e", "f", "g"};
    String[][] dependencies = {{"d", "g"}, {"f", "c"}, {"f", "a"}, {"f", "b"}, {"c", "a"}, {"a", "e"}, {"b", "e"}, {"a", "c"}};

    BuildOrder myclass = new BuildOrder();
    try {
      List<Node> buildOrder = myclass.findBuildOrder(projectNames, dependencies);
      System.out.println("Build Order found: ");
      for(Node nd: buildOrder){
        System.out.print(" " + nd.name + " -> ");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public class Node {
    public String name;
    public List<Node> children;
    public int numOfParent; // used to find out if a node is depend on other nodes

    public Node (String name) {
      this.name = name;
      this.children = new ArrayList<>();
      this.numOfParent = 0;
    }

    public void addChild(Node child) {
      children.add(child);
    }

    public void incrementParentCount() {
      this.numOfParent++;
    }

    public void decrementParentCount() {
      this.numOfParent--;
    }

    /**
     * remove children's dependency on this node (decrease "numOfParent" of their child nodes)
     */
    public void removeChildDependency () {
      for(Node child: children) {
        child.decrementParentCount();
      }
    }
  }

  public class Graph {
    List<Node> nodes;
    Map<String, Node> map; // used for quick find node by given "name"

    public Graph() {
      this.nodes = new ArrayList<>();
      this.map = new HashMap<>();
    }

    public void addNode(Node nd) {
      nodes.add(nd);
      map.put(nd.name, nd);
    }

    public Node getNodeByName(String name) throws Exception {
      if(!map.containsKey(name)){
        throw new Exception("Node with name" + name + "does not exist!");
      }
      return map.get(name);
    }

    public List<Node> orderNodes() throws Exception {
      List<Node> buildOrder = new ArrayList<>();

      List<Node> pendingNodes = this.nodes;

      while (pendingNodes.size() > 0) {

        // step 1. find nodes with no dependencies (numOfParent == 0)
        List<Node> nodeWithNoDependencies = findNodesWithNoDependencies(pendingNodes);

        if (nodeWithNoDependencies.size() > 0) {
          // step 2. add those nodes to "buildOrder", remove those nodes from "pendingNodes"
          for (Node nd : nodeWithNoDependencies) {
            buildOrder.add(nd);
            pendingNodes.remove(nd);

            // step 3. remove children's dependency on this node (decrease "numOfParent" of their child nodes)
            nd.removeChildDependency();
          }
        }
        // Special case handling: loop dependency
        else {
          throw new Exception("Cannot find build order. Most likely is due to loop dependency. ");
        }

      }

      return buildOrder;
    }

    private List<Node> findNodesWithNoDependencies(List<Node> inputNodes) {
      List<Node> outputNodes = new ArrayList<>();
      for(Node nd : inputNodes) {
        if (nd.numOfParent == 0) {
          outputNodes.add(nd);
        }
      }
      return outputNodes;
    }

  }

  /**
   *
   * @param projectNames eg. ["a", "b", "c", "d"]
   * @param dependencies eg. [["a", "d"], ["f", "b"], ["b", "d"]]
   * @return
   */
  public List<Node> findBuildOrder(String[] projectNames, String[][] dependencies) throws Exception{
    Graph g = buildGraph(projectNames, dependencies);
    return g.orderNodes();
  }

  public Graph buildGraph(String[] projectNames, String[][] dependencies) throws Exception{
    Graph g = new Graph();

    // Step 1. Iterate through projectNames, create node for each name, add new created node to graph
    Node newNode;
    for(String name: projectNames) {
      newNode = new Node(name); // children of newNode is empty, will be handled in Step 2.
      g.addNode(newNode); // add both to list and to hashmap
    }

    // Step 2. Iterate through dependencies, add to node's children list.
    for(String[] dependency:dependencies) {
      // find node from the hashmap by given name
      Node parent = g.getNodeByName(dependency[0]);
      Node child = g.getNodeByName(dependency[1]);

      // set node's children
      parent.addChild(child);

      // increase childnode's "numOfParent"
      child.incrementParentCount();
    }

    return g;
  }
}
