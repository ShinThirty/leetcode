package findserversthathandledmostnumberofrequests

import scala.collection.mutable

class ServerRegistry(nServers: Int) {

  // Server ids should be in the range of [taskId, taskId + nServers - 1)
  val availableServers = mutable.SortedSet.from(Range(0, nServers))

  val serverUsage = mutable.IndexedSeq.fill(nServers)(0)

  def reserve(taskId: Int): Option[Int] = {
    val serverOption = availableServers
      .minAfter(taskId)
    serverOption.foreach((serverId) => {
      availableServers -= serverId
      serverUsage(serverId % nServers) += 1
    })
    serverOption
  }

  def release(serverId: Int, taskId: Int): Unit = {
    availableServers += taskId + ((serverId - taskId) % nServers + nServers) % nServers
  }

  def getBusiestServers(): List[Int] = {
    serverUsage.view.zipWithIndex
      .groupMap((entry) => entry._1)((entry) => entry._2)
      .maxBy((entry) => entry._1)
      ._2
      .toList
  }
}

object ServerRegistry {
  def apply(nServers: Int): ServerRegistry = {
    new ServerRegistry(nServers)
  }
}

case class Task(val taskId: Int, val endTime: Int, val serverId: Int)
    extends Ordered[Task] {
  def compare(that: Task): Int = {
    val timeDiff = that.endTime - this.endTime
    if (timeDiff != 0) {
      timeDiff
    } else {
      this.taskId - that.taskId
    }
  }
}

class TaskRegistry(nServers: Int) {

  val taskQueue = mutable.PriorityQueue[Task]()

  def addRunningTask(taskId: Int, endTime: Int, serverId: Int): Unit = {
    taskQueue.enqueue(Task(taskId, endTime, serverId))
  }

  def evictCompletedTasks(currentTime: Int): List[Int] = {
    val freeServerIds = mutable.Buffer[Int]()
    while (taskQueue.headOption.exists((head) => head.endTime <= currentTime)) {
      val completedTask = taskQueue.dequeue()
      freeServerIds += completedTask.serverId
    }
    freeServerIds.toList
  }
}

object TaskRegistry {
  def apply(nServers: Int): TaskRegistry = {
    new TaskRegistry(nServers)
  }
}

object Solution {
  def busiestServers(
      k: Int,
      arrival: Array[Int],
      load: Array[Int]
  ): List[Int] = {

    val serverRegistry = ServerRegistry(k)
    val taskRegistry = TaskRegistry(k)

    arrival.zipWithIndex.foreach {
      case (currentTime, taskId) => {
        taskRegistry
          .evictCompletedTasks(currentTime)
          .foreach(serverId => serverRegistry.release(serverId, taskId))
        serverRegistry
          .reserve(taskId)
          .foreach((serverId) =>
            taskRegistry
              .addRunningTask(taskId, currentTime + load(taskId), serverId)
          )
      }
    }

    serverRegistry.getBusiestServers()
  }
}
