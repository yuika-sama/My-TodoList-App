package com.example.mytodoapp.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodoapp.R
import com.example.mytodoapp.data.model.ToDoData
import com.example.mytodoapp.data.viewmodel.ToDoViewModel
import com.example.mytodoapp.databinding.FragmentUpdateBinding
import com.example.mytodoapp.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentTitleEt: EditText
    private lateinit var currentDescriptionEt: EditText
    private lateinit var currentPrioritiesSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args

        // Gắn UI với dữ liệu
        binding.currentTitleEt.setText(args.currentItem.title)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentPrioritiesSpinner.setSelection(
            mSharedViewModel.parsePriorityToInt(args.currentItem.priority)
        )
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true) // Move to onViewCreated for better lifecycle handling
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemoval() {
//        AlertDialog.Builder(requireContext())
//            .setTitle("Delete ${args.currentItem.title}?")
//            .setMessage("Are you sure you want to remove ${args.currentItem.title}?")
//            .setPositiveButton("Yes") { _, _ ->
//                mToDoViewModel.deleteItem(args.currentItem)
//                Toast.makeText(requireContext(), "Successfully removed: ${args.currentItem.title}", Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.action_fragment_update_to_fragment_list)
//            }
//            .setNegativeButton("No", null)
//            .create()
//            .show()
    }

    private fun updateItem() {
        val title = currentTitleEt.text.toString()
        val description = currentDescriptionEt.text.toString()
        val getPriority = currentPrioritiesSpinner.selectedItem.toString()

        if (mSharedViewModel.verifyDataFromUser(title, description)) {
            val updatedItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_update_to_fragment_list)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}
