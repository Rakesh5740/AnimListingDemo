package com.animlistingdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.animlistingdemo.MainViewModel
import com.animlistingdemo.adapter.AnimListingAdapter
import com.animlistingdemo.data.Employee
import com.animlistingdemo.data.EmployeeList
import com.animlistingdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var dataList: ArrayList<Employee>
    private var adapter: AnimListingAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel.employeeList.observe(this) {
            binding.progressbar.visibility = View.GONE

            if (it.employeeList.isNotEmpty())
                bindData(it)
            else Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(this) {
            if (it) {
                binding.progressbar.visibility = View.VISIBLE
            }
        }

        viewModel.isError.observe(this) {
            if (it) {
                binding.progressbar.visibility = View.GONE
            }
        }

        binding.swipeToRefresh.setOnRefreshListener {

            // on below line we are setting is refreshing to false.
            binding.swipeToRefresh.isRefreshing = false
            dataList.clear()
            adapter?.notifyDataSetChanged()
            viewModel.fetchDetails()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerview.setHasFixedSize(true)
        dataList = ArrayList()
        adapter = AnimListingAdapter(this@MainActivity, dataList)
        binding.recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindData(response: EmployeeList) {
        for (employee in response.employeeList) {
            employee?.let {
                dataList.add(it)
            }
        }
        adapter?.notifyDataSetChanged()
    }

}