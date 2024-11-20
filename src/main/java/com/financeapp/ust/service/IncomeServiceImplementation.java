package com.financeapp.ust.service;

import com.financeapp.ust.dto.IncomeDto;
import com.financeapp.ust.dto.summaryDto.IncomeSummaryDto;
import com.financeapp.ust.model.Income;
import com.financeapp.ust.model.User;
import com.financeapp.ust.repository.IncomeRepository;
import com.financeapp.ust.repository.UserRepository;
import com.financeapp.ust.util.IncomeEntityConversion;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IncomeServiceImplementation implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public IncomeDto save(int id, IncomeDto incomeDto) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        Income income = IncomeEntityConversion.DtoToEntityConversion(incomeDto);
        income.setUser(user);
        user.getIncomes().add(income);

        income = incomeRepository.save(income);

        return IncomeEntityConversion.EntityToDtoConversation(income);
    }

    @Override
    public List<IncomeSummaryDto> getAllIncomes(int userId) {
        return incomeRepository.findAll()
                .stream()
                .filter(i -> i.getUser().getId() == userId)
                .map(i -> new IncomeSummaryDto(i.getId(), i.getAmount(), i.getSource(), i.getDate()))
                .toList();
    }

    @Override
    public IncomeDto updateById(int userId, String source, IncomeDto incomeDto) {

        Income income = incomeRepository.findByIdAndSource(userId, source);
        if (income == null) {
            throw new EntityNotFoundException("Income with source " + source + " not found for user ID " + userId);
        }

        if (incomeDto.amount() > 0) {
            income.setAmount(incomeDto.amount());
        }
        if (incomeDto.source() != null) {
            income.setSource(incomeDto.source());
        }
        if (incomeDto.date() != null) {
            income.setDate(incomeDto.date());
        }

        //return save(userId,IncomeEntityConversion.EntityToDtoConversation(income));
        Income updatedIncome = incomeRepository.save(income);
        return IncomeEntityConversion.EntityToDtoConversation(updatedIncome);
    }

    @Override
    public void deleteByIdAndSource(int userId, String source) {
        incomeRepository.deleteByIdAndSource(userId, source);
    }
}
