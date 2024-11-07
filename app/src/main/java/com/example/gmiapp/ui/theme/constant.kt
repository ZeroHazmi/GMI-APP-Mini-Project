package com.example.gmiapp.ui.theme

import com.example.gmiapp.ui.theme.CourseData.computerInformationCourses
import com.example.gmiapp.ui.theme.CourseData.electricalCourses
import com.example.gmiapp.ui.theme.CourseData.mechanicalCourses

data class Course(
    val id: Int = generateCourseId(),
    val name: String,
    val departmentId: Int? = null,
    val programmeId: Int? = null,
    val description: String?,
    val prerequisites: List<String> = emptyList()
){
    companion object {
        private var courseIdCounter = 0
        private fun generateCourseId(): Int = courseIdCounter++
    }
}

data class Department(
    val id: Int,
    val name: String,
    val course: List<Course> = emptyList()
)

data class Programme(
    val id: Int,
    val name: String,
    val departments: List<Department> = emptyList(),
    val courses: List<Course> = emptyList()
)

data class SPMGrade(
    val id: String,
    val value: Int
) {
    companion object {
        val G = SPMGrade("G", 0)
        val E = SPMGrade("E", 1)
        val D = SPMGrade("D", 2)
        val C = SPMGrade("C", 3)
        val CPlus = SPMGrade("C+", 4)
        val B = SPMGrade("B", 5)
        val BPlus = SPMGrade("B+", 6)
        val AMinus = SPMGrade("A-", 7)
        val A = SPMGrade("A", 8) // "A" has the same value as "A-" in your setup
        val APlus = SPMGrade("A+", 9)

        // List of all grades
        val spmGrades = listOf(G, E, D, C, CPlus, B, BPlus, AMinus, A, APlus)
    }
}

data class SPMSubject(
    val name: String
)

data class SPMResult(val subject: SPMSubject, val grade: SPMGrade)

//

// Initialize courses for each department

object CourseData{
    val electricalCourses = listOf(
        Course(name = "Mechatronics", departmentId = 0, description = ""),
        Course(name = "Electronics & Information Technology", departmentId = 0, description = ""),
        Course(name = "Sustainable Energy & Power Distribution", departmentId = 0, description = ""),
        Course(name = "Process Instrumentation & Control", departmentId = 0, description = ""),
        Course(name = "Autotronics Engineering Technology", departmentId = 0, description = ""),
    )

    val mechanicalCourses = listOf(
        Course(name = "Precision Tooling Engineering Technology", departmentId = 1, description = ""),
        Course(name = "Industrial Design", departmentId = 1, description = ""),
        Course(name = "Industrial Quality Engineering", departmentId = 1, description = ""),
        Course(name = "Innovative Product Design", departmentId = 1, description = ""),
        Course(name = "CNC Precision Technology", departmentId = 1, description = ""),
        Course(name = "Machine Tools Maintenance", departmentId = 1, description = ""),
        Course(name = "Manufacturing System", departmentId = 1, description = ""),
    )

    val computerInformationCourses = listOf(
        Course(name = "Software Engineering", departmentId = 2, description = ""),
        Course(name = "Cyber Security Technology", departmentId = 2, description = ""),
        Course(name = "Creative Multimedia", departmentId = 2, description = "")
    )

    val gappCourse = Course(name = "German University Preparatory Programme (GAPP)", programmeId = 1, description = "")
    val gufpCourse = Course(name = "GMI-UTP Foundation Programme (GUFP)", programmeId = 2, description = "")
}

object AllCourse{
    val course = electricalCourses + mechanicalCourses + computerInformationCourses
}

// Initialize Departments
object DepartmentData{
    val departments = listOf(
        Department(name = "Mechanical Engineering",course = mechanicalCourses, id = 0),
        Department(name = "Electrical Engineering",course = electricalCourses, id = 1),
        Department(name = "Computer and Information",course = computerInformationCourses, id = 2)
    )
}

//Initialize Programme
object PreUniData{
    val programmes = listOf(
        CourseData.gappCourse,
        CourseData.gufpCourse
    )
}

//Initialize SPM Grades
object SPMGradesData{
    val spmGrades = listOf(
        SPMGrade(id = "G", value = 0), // value = 0
        SPMGrade(id = "E", value = 1), // value = 1
        SPMGrade(id = "D", value = 2), // value = 2
        SPMGrade(id = "C", value = 3), // value = 3
        SPMGrade(id = "C+", value = 4), // value = 4
        SPMGrade(id = "B", value = 5), // value = 5
        SPMGrade(id = "B+", value = 6), // value = 6
        SPMGrade(id = "A-", value = 7), // value = 7
        SPMGrade(id = "A", value = 8), // value = 8
        SPMGrade(id = "A+", value = 9), // value = 9
    )
}

object SPMSubjects {

    // Compulsory Subjects
    val compulsorySubjects = listOf(
        SPMSubject("Bahasa Melayu"),
        SPMSubject("Bahasa English"),
        SPMSubject("Pendidikan Islam"),
        SPMSubject("Pendidikan Moral"),
        SPMSubject("Sejarah"),
        SPMSubject("Matematik")
    )

    // Science and Technical Subjects
    val scienceAndTechnicalSubjects = listOf(
        SPMSubject("Sains"),
        SPMSubject("Matematik Tambahan"),
        SPMSubject("Fizik"),
        SPMSubject("Kimia"),
        SPMSubject("Biologi"),
        SPMSubject("Sains Tambahan")
    )

    // Optional Subjects (if applicable)
//    val optionalSubjects = listOf(
//        // You can add other optional subjects here if needed
//    )

    // All Subjects (combining compulsory and science/technical subjects)
    val allSubjects = compulsorySubjects + scienceAndTechnicalSubjects
}

data class SPMEnquiryRequirements(
    val bahasaMelayu: SPMGrade = SPMGradesData.spmGrades.first { it.id == "C" },
    val sejarah: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" },
    val english: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" },
    val mathematics: SPMGrade = SPMGradesData.spmGrades.first { it.id == "C" },
    val scienceOrTechnical: SPMGrade = SPMGradesData.spmGrades.first { it.id == "C" },
    val otherSubject: SPMGrade = SPMGradesData.spmGrades.first { it.id == "C" }
)

data class CRMEnquiryRequirements(
    val bahasaMelayu: SPMGrade = SPMGradesData.spmGrades.first { it.id == "C" },
    val sejarah: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" },
    val random1: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" },
    val random2: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" },
    val random3: SPMGrade = SPMGradesData.spmGrades.first { it.id == "E" }
)



