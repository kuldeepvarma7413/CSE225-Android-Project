package screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.studysync.R

class Report_Bug_Feature : AppCompatActivity() {

    lateinit var radioGroup: RadioGroup
    lateinit var bugBtn: RadioButton
    lateinit var featureBtn: RadioButton
    lateinit var detailEditText: EditText
    lateinit var nameEditText: EditText
    lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_bug_feature)
        // initilize variables
        radioGroup=findViewById(R.id.radio_group)
        bugBtn=findViewById(R.id.bug_ratio_btn)
        featureBtn=findViewById(R.id.feature_ratio_btn)
        detailEditText=findViewById(R.id.detail)
        nameEditText=findViewById(R.id.name)
        submit=findViewById(R.id.submit_btn)

        var reportType: String =""

        radioGroup.setOnCheckedChangeListener{_, checkedId->
            when(checkedId){
                R.id.bug_ratio_btn ->{
                    reportType = "Bug"
                    Toast.makeText(this, "Bug button selected", Toast.LENGTH_SHORT).show()
                }
                R.id.feature_ratio_btn ->{
                    reportType = "Feature"
                    Toast.makeText(this, "Feature button selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        submit.setOnClickListener {
            // check if both field are filled
            var details: String = detailEditText.text.toString()
            var name: String = nameEditText.text.toString()

            if(details.length==0){
                Toast.makeText(this, "Please in details field", Toast.LENGTH_SHORT).show()
            }else if(name.length==0){
                Toast.makeText(this, "Please in name field", Toast.LENGTH_SHORT).show()
            }else{
                // handle in backend
                Toast.makeText(this, "Thank you, we'll look into it. ${ reportType }", Toast.LENGTH_SHORT).show()
            }
        }
    }
}