package com.example.jmren.test
import org.opentest4j.*
import org.apiguardian.*
import com.example.jmren.checklistii.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskMembersTest {

    @Test
    fun getHasDL() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.HasDL, false)
    }

    @Test
    fun setHasDL() {
        var test : Task = Task("Example Task", "Example Desc")
        test.HasDL = true
        assertEquals(test.HasDL, true)
    }

    @Test
    fun getDeadlineDefault() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.Deadline, "")
    }

    @Test
    fun setDeadline() {
        var test : Task = Task("Example Task", "Example Desc")
        test.Deadline = "Example Time"
        assertEquals(test.Deadline, "Example Time")
    }

    @Test
    fun getCompleted() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.completed, false)
    }

    @Test
    fun setCompleted() {
        var test : Task = Task("Example Task", "Example Desc")
        test.completed = true
        assertEquals(test.completed, true)
    }

    @Test
    fun getCompdatetime() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.compdatetime, "")
    }

    @Test
    fun setCompdatetime() {
        var test : Task = Task("Example Task", "Example Desc")
        test.compdatetime = "07/09/2000"
        assertEquals(test.compdatetime, "07/09/2000")
    }

    @Test
    fun getCompby() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.compby, null)
    }

    @Test
    fun getName() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.name, "Example Task")
    }

    @Test
    fun setName() {
        var test : Task = Task("Example Task", "Example Desc")
        test.name = "Example 2"
        assertEquals(test.name, "Example 2")
    }

    @Test
    fun getDesc() {
        val test : Task = Task("Example Task", "Example Desc")
        assertEquals(test.desc, "Example Desc")
    }

    @Test
    fun setDesc() {
        var test : Task = Task("Example Task", "Example Desc")
        test.desc = "Desc 2"
        assertEquals(test.desc, "Desc 2")
    }
}